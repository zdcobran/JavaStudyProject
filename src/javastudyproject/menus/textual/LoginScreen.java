/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.reporting.SystemReporter;
import javastudyproject.users.LoginException;
import javastudyproject.users.UserSystem;

/**
 *
 * @author EladYarkoni
 */
public class LoginScreen {

    private BufferedReader reader;
    private UserSystem userSystem;
    private String user;
    private String password;

    public LoginScreen(String title, String version) {

        reader = new BufferedReader(new InputStreamReader(System.in));
        userSystem = new UserSystem();

        System.out.println("--------------------------------------------\n"
                +          "Welcome to "+title+" version: "+version+"\n"
                +          "--------------------------------------------\n");

        try {
            System.out.print("User: ");
            user = reader.readLine();
            System.out.print("Password: ");
            password = reader.readLine();
            String userType = userSystem.Authentication(user, password);
        }
        catch (IOException ex) {}
        catch (LoginException lex) {
            System.out.println(lex.getMessage());
        }
    }

}
