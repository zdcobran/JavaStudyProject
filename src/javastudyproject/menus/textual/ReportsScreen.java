/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.users.UserOps;
import javastudyproject.*;
/**
 *
 * @author eyarkoni
 */
public class ReportsScreen {

    private BufferedReader reader;

    public ReportsScreen() {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Reports menu\n-----------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("1. Print all users");
        System.out.println("2. Print user information");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("3. Print all orders");
        System.out.println("4. Print orders by state");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("5. Print all products");
        System.out.println("6. Print products by price");
        System.out.println("7. Print  products by category");
        System.out.println("8. Print product orders from history");
        System.out.println("9. Print best selling product");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("10. Print all categories");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("11. Back to main menu\n");
        System.out.print("Your choise: ");

        try {
           int choise=  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1: { 
                    UserOps.printAllUsers();
                }break;
                case 2: {
                    System.out.println("Enter username: ");
                    String userName = reader.readLine();
                    UserOps.printUserInfo(userName);
                }break;
                case 3: {
                    OrederOps.printAllOrders();
                }break;
                case 4: {
                    System.out.print("Select Status(1. New 2. Pending 3. Ready 4. InProgress 5. Finished): ");
                    int stateChosen = Integer.parseInt(reader.readLine());
                    Order.StateType state = null;
                    switch (stateChosen) {
                        case 1: {state = Order.StateType.New;}break;
                        case 2: {state = Order.StateType.Pending;}break;
                        case 3: {state = Order.StateType.Ready;}break;
                        case 4: {state = Order.StateType.InProgress;}break;
                        case 5: {state = Order.StateType.Finished;}break;
                    }
                    OrederOps.printOrdersByState(state);
                }break;
                case 5: {
                    ProductsOps.printAllProducts();
                }break;
                case 6: {
                    // print products by price function...
                }break;
                case 7: {
                    // print products by price category...
                }break;
                case 8: {
                    // print products orders from history function ...
                }break;
                case 9: {
                    // print best selling product
                }break;
                case 10: {
                    // print all categories...
                }break;
                case 11: {
                    new AdministratorScreen();
                }break;
            }
            new AdministratorScreen();
        } catch (IOException ex) {}
        catch (Exception ex) {}
    }




    
}
