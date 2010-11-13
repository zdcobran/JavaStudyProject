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
import javastudyproject.users.LoginException;

/**
 *
 * @author EladYarkoni
 */
public class LoginScreen extends ObjectSystem {

    private BufferedReader reader;
    private String user;
    private String password;

    public LoginScreen(String title, String version) {

        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("------------------------------------------------------------------------\n"
                +          "Welcome to "+title+" version: "+version+"\n"
                +          "------------------------------------------------------------------------\n");

        try {
            System.out.print("User: ");
            user = reader.readLine();
            System.out.print("Password: ");
            password = reader.readLine();
            User.UserType userType = authenticate(user, password);

            if (userType.equals(User.UserType.Administrator)) {
                new AdministratorScreen();
            }
            else if (userType.equals(userType.ReadWriteUser)) {
                new ReadWriteUserScreen();
            }
        }
        catch (IOException ex) {}

    }

}
