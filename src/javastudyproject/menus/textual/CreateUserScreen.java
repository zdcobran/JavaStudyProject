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

/**
 *
 * @author eyarkoni
 */
public class CreateUserScreen extends ObjectSystem{

    private BufferedReader reader;

    public CreateUserScreen() {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("New User\n-----------------------------------------------");
        try {
            System.out.print("User ID: ");
            String id = reader.readLine();
            System.out.print("First name: ");
            String name = reader.readLine();
            System.out.print("Last name: ");
            String lastName = reader.readLine();
            System.out.print("Email: ");
            String email = reader.readLine();
            System.out.print("Password: ");
            String password = reader.readLine();
            System.out.print("Age: ");
            String age = reader.readLine();
            ReadOnlyUser newuser = new ReadOnlyUser(id,name,lastName,email,password,age);
            users.add(newuser);

        } catch (IOException ex) {}
    }





}