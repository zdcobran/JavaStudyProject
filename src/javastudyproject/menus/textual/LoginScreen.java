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

    public LoginScreen(String title, String version) throws Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));

        SystemReporter.report("------------------------------------------------------------------------\n");
        SystemReporter.report("Welcome to "+title+" version: "+version+"\n");
        SystemReporter.report("------------------------------------------------------------------------\n");

        try {
            SystemReporter.report("User: ");
            user = reader.readLine();
            SystemReporter.report("Password: ");
            password = reader.readLine();
            UserOps.UserType userType = UserOps.authenticate(user, password);

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
