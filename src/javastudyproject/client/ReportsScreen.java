/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.client;

import javastudyproject.service.ProductsOpsBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javastudyproject.model.*;
import javastudyproject.reporting.SystemReporter;

/**
 * Contains all administrator reports in the system
 * @author eyarkoni
 */
public class ReportsScreen extends OrdersScreen{

    private BufferedReader reader;

    public ReportsScreen() throws IOException, Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nReports menu\n-----------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("1. Print all users");
        System.out.println("2. Print user information");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("3. Print all orders");
        System.out.println("4. Print orders by state");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("5. Print all products");
        System.out.println("6. Print products larger/smaller than specific price");
        System.out.println("7. Print  products by category");
        System.out.println("8. Print product orders from history");
        System.out.println("9. Print best selling product");
        System.out.println("10. Print products by price (decreasing sort)");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("11. Print all categories");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("12. Back to main menu\n");
        System.out.print("Your choise: ");

        try {
           int choise=  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1: { 
                    userService.printAllUsers();
                }break;
                case 2: {
                    System.out.println("Enter username: ");
                    String userName = reader.readLine();
                    userService.printUserInfo(userName);
                }break;
                case 3: {
                    orderService.printAllOrders();
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
                    orderService.printOrdersByState(state);
                }break;
                case 5: {
                    productService.printAllProducts();
                }break;
                case 6: {
                    System.out.print("1. Larger 2. Smaller : ");
                    int stateChosen = Integer.parseInt(reader.readLine());
                    System.out.print("\nThan number : ");
                    double number = Double.parseDouble(reader.readLine());

                    switch (stateChosen)
                    {
                        case 1:
                            productService.printProductsByPrice(ProductsOpsBean.LergerSmaller.Larger, number);
                            break;
                        case 2:
                            productService.printProductsByPrice(ProductsOpsBean.LergerSmaller.Smaller, number);
                            break;
                    }
                }break;
                case 7: {
                    List<Category> categories = categoryService.getAllCategories();
                    System.out.print("All categories: ");
                    for (int i = 0; i < categories.size(); i++)
                    {
                        System.out.print(i+ 1 + ". " + categories.get(i).getName());
                    }
                    System.out.print("Choose category index: ");
                    int catIndex = Integer.parseInt(reader.readLine());
                    productService.printProductsByCategory(categories.get(catIndex - 1));
                }break;
                case 8: {
                    List<Product> products = productService.getAllProducts();
                    if (products.isEmpty())
                    {
                        SystemReporter.report("There are no products in the system", true);
                    }
                    System.out.print("All products: ");
                    for (int i = 0; i < products.size(); i++)
                    {
                        System.out.print(i+ 1 + ". " + products.get(i).getName());
                    }
                    System.out.print("Type product index: ");
                    int productIndex = Integer.parseInt(reader.readLine());
                    orderService.printOrdersUserNamesByPurchasedProduct(products.get(productIndex -1));
                }break;
                case 9: {
                    productService.printMostSaleableProduct();
                }break;
                case 10: {
                    productService.printSortedProductsByPrice();
                }break;
                case 11: {
                    productService.printAllCategories();
                }break;
                case 12: {
                    new AdministratorScreen();
                }break;
            }
            System.out.println("\n1. View another report");
            System.out.println("2. Back to main menu\n");

            System.out.println("Ente your choise: ");

            int newChoise =  Integer.parseInt(reader.readLine());
            switch (newChoise)
            {
                case 1:
                    new ReportsScreen();
                    break;
                case 2:
                    new AdministratorScreen();
                    break;
            }

        } 
        catch (Exception e)
        {
            System.out.println("Failed to show the report, error: " + e.getMessage());
            System.out.println("1. Try again");
            System.out.println("2. Back to main menu\n");

            System.out.println("Ente your choise: ");

            int choise =  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1:
                    new ReportsScreen();
                    break;
                case 2:
                    new AdministratorScreen();
                    break;
            }
        }
    }




    
}
