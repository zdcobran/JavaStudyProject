/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.client;

import javastudyproject.model.User;
import javastudyproject.service.UserOpsBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.service.ServiceSystem;
import javastudyproject.reporting.SystemReporter;

/**
 * This is the login screen which is the first screen of the program
 * @author EladYarkoni
 */
public class LoginScreen extends ServiceSystem {

    private BufferedReader reader;
    private String userName;
    private String password;


    public LoginScreen() throws Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("------------------------------------------------------------------------\n");
        System.out.println("Welcome to ERProduct version: 0.2 alpha \n");
        System.out.println("------------------------------------------------------------------------\n");
        int loginRetries = 0;
        int maxRetries = 3;
        boolean isConnected = false;
        try
        {
            while (!isConnected && loginRetries != maxRetries)
            {
                isConnected = LoginOperationImpl();
                loginRetries++;
            }
            if (!isConnected)
                SystemReporter.report(
                    "Reached " + maxRetries + " login retries. Existing...", true);
        }
        catch (Exception e)
        {
            reader.close();
            SystemReporter.report(e.getMessage());
        }
    }

    /**
     * do the logins attempts
     * @return true if login success or false
     * @throws IOException
     * @throws Exception
     */
    private boolean LoginOperationImpl() throws IOException, Exception
    {
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("User: ");
            userName = reader.readLine();
            System.out.print("Password: ");
            password = reader.readLine();
            User user = new User();
            try
            {
                user = userService.authenticate(userName, password);
            }
            catch (Exception e)
            {
                SystemReporter.report(e.getMessage());
                return false;
            }
            if (user.toString().equals("AdministratorUser")) {
                new AdministratorScreen();
            }
            else if (user.toString().equals("ReadWriteUser")) {
                new ReadWriteUserScreen(user);
            }
            else if (user.toString().equals("ReadOnlyUser")) {
                new ReadOnlyUserScreen(user);
            }
            return true;

    }
}
