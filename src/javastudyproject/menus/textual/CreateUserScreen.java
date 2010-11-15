/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.ObjectSystem;
import javastudyproject.reporting.SystemReporter;
import javastudyproject.users.*;

/**
 *
 * @author eyarkoni
 */
public class CreateUserScreen extends ObjectSystem{

    private BufferedReader reader;

    public CreateUserScreen() throws Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));
        SystemReporter.report("New User\n-----------------------------------------------");
        try {
            SystemReporter.report("User name: ");
            String userName = reader.readLine();
            SystemReporter.report("User ID: ");
            String id = reader.readLine();
            SystemReporter.report("First name: ");
            String name = reader.readLine();
            SystemReporter.report("Last name: ");
            String lastName = reader.readLine();
            SystemReporter.report("Email: ");
            String email = reader.readLine();
            SystemReporter.report("Password: ");
            String password = reader.readLine();
            SystemReporter.report("Age: ");
            String age = reader.readLine();
            ReadWriteUser newuser = new ReadWriteUser(userName,id,name,lastName,email,password,age);
            users.add(newuser);

        } catch (IOException ex) {
            SystemReporter.report("Catched I/O exception: " + ex.getMessage());
            reader.close();
        }
        reader.close();
    }





}
