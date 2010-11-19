/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.*;
import javastudyproject.OrederOps;
import javastudyproject.reporting.SystemReporter;

/**
 *
 * @author eyarkoni
 */
public class OrdersScreen extends ObjectSystem{

    private BufferedReader reader;

    public OrdersScreen() throws Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Orders  menu\n-----------------------------------------------");
        System.out.println("1. Update order status");
        System.out.println("2. Delete order");
        System.out.println("3. Back to main menu\n");

        System.out.print("Your choise: ");

        try {
           int choise=  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1: {
                    System.out.print("Choose order: ");
                    for (int i = 0; i < orders.size(); i++)
                    {
                       SystemReporter.report( i + 1 + ". Run ID: "
                               + orders.get(i).getRunId() + " of user: "
                               + orders.get(i).getUser().getUserName());
                    }
                    int id = Integer.parseInt(reader.readLine());
                    System.out.print("Enter new state (1. New 2. Pending 3. Ready 4. InProgress 5. Finished): ");
                    int stateChosen = Integer.parseInt(reader.readLine());
                    Order.StateType state = Order.StateType.New;
                    switch (stateChosen) {
                        case 1: {state = Order.StateType.New;}break;
                        case 2: {state = Order.StateType.Pending;}break;
                        case 3: {state = Order.StateType.Ready;}break;
                        case 4: {state = Order.StateType.InProgress;}break;
                        case 5: {state = Order.StateType.Finished;}break;
                    }
                    OrederOps.updateOrderStatus(id - 1, state);
                }break;
                 case 2: {
                    System.out.print("Choose order: ");
                    for (int i = 0; i < orders.size(); i++)
                    {
                       SystemReporter.report( i + 1 + ". Run ID: "
                               + orders.get(i).getRunId() + " of user: "
                               + orders.get(i).getUser().getUserName());
                    }
                    int id = Integer.parseInt(reader.readLine());
                    OrederOps.deleteOrder(id - 1);
                }break;
                 case 3: {
                    new AdministratorScreen();
                }break;
            }
        } catch (IOException ex) {}
    }





}
