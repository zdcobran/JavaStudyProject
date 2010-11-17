/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.*;
import javastudyproject.reporting.SystemReporter;
import javastudyproject.users.ReadOnlyUser;
import javastudyproject.users.User;

/**
 *
 * @author eyarkoni
 */
public class ReadOnlyUserScreen extends ObjectSystem {

    private BufferedReader reader;
    private User workingUser;

    public ReadOnlyUserScreen(User user) throws Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));
        workingUser = user;
        System.out.println("User  menu\n-----------------------------------------------");
        System.out.println("1. Print viewable orders");
        System.out.println("2. Exit program\n");
        System.out.print("Your choise: ");

        try {
            int choise=  Integer.parseInt(reader.readLine());
            switch (choise) {
                case 1:
                {
                    PrintGrantedOrders(workingUser);
                }
                break;
                case 2:
                {
                    new LoginScreen();
                }
                break;
            }
            new ReadOnlyUserScreen(workingUser);
        }
       catch (IOException ex) {}
       catch (Exception e)
       {
            SystemReporter.report("Failed to perform last action, error: " + e.getMessage());
            new ReadOnlyUserScreen(workingUser);
       }
    }

    public void PrintGrantedOrders(User user) throws Exception
    {
        boolean isPrinted = false;
        for (Order order: orders)
        {
            if (order.getUser().getUserName().equals(
                    ((ReadOnlyUser)user).getOwningUserName()))
            {
                for (Product product : order.getProducts())
                {
                    isPrinted = true;
                    ProductsOps.printProductInfoImpl(product);
                }
            }
        }
        if (!isPrinted)
            SystemReporter.report("No granted orders available");
    }
}
