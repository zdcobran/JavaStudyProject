/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.users.UserSystem;

/**
 *
 * @author eyarkoni
 */
public class ReadWriteUserScreen {

    private BufferedReader reader;

    public ReadWriteUserScreen() {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("User  menu\n-----------------------------------------------");
        System.out.println("1. Create new user");
        System.out.println("2. Update exist user");
        System.out.println("3. Create Order");
        System.out.println("4. Create Read only access user\n");

        System.out.print("Your choise: ");

        try {
            int choise=  Integer.parseInt(reader.readLine());
            switch (choise) {
                case 1: {
                    new CreateUserScreen(UserSystem.UserType.ReadWriteUser);
                } break;
            }

        } catch (IOException ex) {}
    }





}
