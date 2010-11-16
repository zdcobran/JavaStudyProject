/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.users.UserOps;

/**
 *
 * @author eyarkoni
 */
public class CustomersScreen {

    private BufferedReader reader;

    public CustomersScreen() {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Customers  menu\n-----------------------------------------------");
        System.out.println("1. Add new user");
        System.out.println("2. Update exist user");
        System.out.println("3. Delete user");
        System.out.println("4. Print user info\n");
        System.out.println("5. Back to main menu\n");
        System.out.print("Your choise: ");

        try {
           int choise=  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1: { 
                    AddNewUser();
                }break;
                case 2: {
                    UpdateExist();
                }break;
                case 3: {
                    System.out.print("Select user id to delete:  ");
                    String userID = reader.readLine();
                    // delete user by user id...
                }break;
                case 4: {
                    System.out.print("Select user id:  ");
                    String userID = reader.readLine();
                    // print user info by user id...
                }break;
                case 5: {
                    new AdministratorScreen();
                }break;
            }
        } catch (IOException ex) {}
    }

    private void AddNewUser() {

        try {
            System.out.print("user type (1. Administrator 2. ReadWrite 3. ReadOnly): ");
            int userType = Integer.parseInt(reader.readLine());
            System.out.print("first name: ");
            String origName = reader.readLine();
            System.out.print("last name: ");
            String lastName = reader.readLine();
            System.out.print("user id: ");
            String id = reader.readLine();
            System.out.print("user name: ");
            String userName = reader.readLine();
            System.out.print("user password: ");
            String password = reader.readLine();
            System.out.print("user email: ");
            String email = reader.readLine();
            System.out.print("user age: ");
            String age = reader.readLine();
            switch (userType) {
                case 1: {
                    // adding administrator
                    UserOps.addNewUser(UserOps.UserType.Administrator, userName, id, origName, lastName, email, password, age);
                }break;
                case 2: {
                    UserOps.addNewUser(UserOps.UserType.ReadWriteUser, userName, id, origName, lastName, email, password, age);
                }break;
                case 3: {
                    UserOps.addNewUser(UserOps.UserType.ReadOnlyUser, userName, id, origName, lastName, email, password, age);
                }break;
            }
        }
        catch (IOException ee) {}
        catch (Exception ee) {}
    }

 private void UpdateExist() {

        try {
            System.out.print("Select user name to update: ");
            String  username = reader.readLine();
            System.out.println("Select field to update (1. first name 2. last name 3. email 4.password 5. age):");
            int fieldchoise = Integer.parseInt(reader.readLine());
            switch (fieldchoise)
            {
                case 1:
                {

                }break;
                case 2:
                {

                }break;
                case 3:
                {

                }break;
                case 4:
                {

                }break;
                case 5:
                {

                }break;
            }
        }
        catch (IOException ee) {}
         catch (Exception ee) {}
    }


}
