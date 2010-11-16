/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javastudyproject.*;

/**
 *
 * @author eyarkoni
 */
public class ReadWriteUserScreen extends ObjectSystem {

    private BufferedReader reader;

    public ReadWriteUserScreen() {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("User  menu\n-----------------------------------------------");
        System.out.println("1. Create new user");
        System.out.println("2. Update exist user");
        System.out.println("3. Create Order");
        System.out.println("4. Back to main menu\n");
        System.out.print("Your choise: ");

        try {
            int choise=  Integer.parseInt(reader.readLine());
            switch (choise) {
                case 1: {
                    CreateNewUser();
                } break;
                case 2: {
                   UpdateExistUser();
                } break;
                case 3: {
                    CreateNewOrder();
                }
                 case 4: {
                    new ReadWriteUserScreen();
                }
            }

        } catch (IOException ex) {}
           catch (Exception ex) {}
    }

    private void  CreateNewUser()
    {
        try {
            System.out.print("user type (1. ReadWrite 3. ReadOnly): ");
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
                    // adding ReadWrite
                }break;
                case 2: {
                    // connect user to his order - not understandable...
                    // adding ReadOnly
                }break;
            }
        }
        catch (IOException ee) {}



    }

    private void UpdateExistUser()
    {
        try {
            System.out.print("Select user id: ");
            String  userID = reader.readLine();

            System.out.print("first name: ");
            String origName = reader.readLine();
            System.out.print("last name: ");
            String lastName = reader.readLine();
            System.out.print("user name: ");
            String userName = reader.readLine();
            System.out.print("user password: ");
            String password = reader.readLine();
            System.out.print("user email: ");
            String email = reader.readLine();
            System.out.print("user age: ");
            String age = reader.readLine();
            // update user by id userID ...
        }
        catch (IOException ee) {}





    }

    public void CreateNewOrder()
    {
        ArrayList<Product> myProductList = new ArrayList<Product>();
        try {
            System.out.println("Products list\n----------------------------");
            for (int i=0; i<products.size();i++)
            {
                System.out.println(i+1+". "+products.get(i).getName());
            }

            System.out.print("Add product number (Type '0' close list): ");
            int selectedProductNumber = 1;
            while (selectedProductNumber != 0)
            {
                selectedProductNumber = Integer.parseInt(reader.readLine());
                Product selectedProduct = products.get(selectedProductNumber-1);
                myProductList.add(selectedProduct);
            }

            System.out.print("Select Delivery type (1. Self 2. Shipping): ");
            int deliveryTypeNum = Integer.parseInt(reader.readLine());
            Order.DeliveryType deliveryType;
            switch (deliveryTypeNum)
            {
                case 1: {deliveryType =Order.DeliveryType.Self; }break;
                case 2: {deliveryType =Order.DeliveryType.Shipping; }break;
            }

            // function to create the order...

        } catch (IOException ex)  {}
    }


}
