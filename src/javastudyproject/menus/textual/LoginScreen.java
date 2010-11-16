/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.ObjectSystem;
import javastudyproject.users.*;
import javastudyproject.reporting.SystemReporter;

/**
 *
 * @author EladYarkoni
 */
public class LoginScreen extends ObjectSystem {

    private BufferedReader reader;
    private String user;
    private String password;
    private static final String DEFAULT_ADMIN = "admin";
    private static final String DEFAULT_ADMIN_PASS = "123456";

    public LoginScreen(String title, String version) throws Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("------------------------------------------------------------------------\n");
         System.out.println("Welcome to "+title+" version: "+version+"\n");
         System.out.println("------------------------------------------------------------------------\n");

        try {
            System.out.print("User: ");
            user = reader.readLine();
            System.out.print("Password: ");
            password = reader.readLine();
            UserOps.UserType userType;
            if ((user.equals(DEFAULT_ADMIN))&&(password.equals(DEFAULT_ADMIN_PASS)))
                userType = UserOps.UserType.Administrator;
            else
                userType = UserOps.authenticate(user, password);

            if (userType.equals(UserOps.UserType.Administrator)) {
                new AdministratorScreen();
            }
            else if (userType.equals(userType.ReadWriteUser)) {
                new ReadWriteUserScreen();
            }
        }
        catch (IOException ex) {}

    }

}
