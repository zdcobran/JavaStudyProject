/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.reporting;
import java.util.ArrayList;
import javastudyproject.Category;
import javastudyproject.Order;
import javastudyproject.Order.StateType;
import javastudyproject.Product;
import javastudyproject.users.User;

/**
 *
 * @author alon
 */
public class InternalReporter {

    /**
     * Print all user
     * @param users - all users list
     * @throws Exception
     */
    public static void printAllUsers(ArrayList<User> users) throws Exception
    {
        for (User user : users)
        {
            SystemReporter.report("Basic user info: ", new String[] {
                "User ID:\t" + user.getId(),
                "User email:\t" + user.getEmail(),
                "User first name:\t" + user.getFirstName(),
                "User last name:\t" + user.getLastName(),
                "User Authorizations:\t" + user.toString()
            });
        }
    }

    /**
     * print specific user detail when the detail provided by ID
     * @param desiredUserId - User id to print
     * @param users - all users list
     * @param orders - all order list
     * @throws Exception
     */

    public static void printUserDetail(String desiredUserId, ArrayList<User> users, ArrayList<Order> orders) throws Exception
    {
        for (User user : users)
        {
            if (user.getId() == desiredUserId)
            {
                SystemReporter.report("Basic user info: ", new String[] {
                    "User ID:\t" + user.getId(),
                    "User email:\t" + user.getEmail(),
                    "User first name:\t" + user.getFirstName(),
                    "User last name:\t" + user.getLastName(),
                    "User Authorizations:\t" + user.toString()
                });
                for (Order order : orders)
                {
                    if(order.getUser() == user)
                    {
                        SystemReporter.report("Users " + user.getId() + " order: ", new String[] {
                            "Order ID:\t" + order.getRunId(),
                            "Order date:\t" + order.getOrderDate(),
                            "Order deliviry date:\t" + order.getDeliveryDate().toString(),
                            "Order deliviry type:\t" + order.getDeliveryType().toString(),
                            "Order state:\t" + order.getState().toString()
                        });
                    }
                }
               continue;
            }
        }
    }

    /**
     * Printing all orders
     * @param orders the whole order list to print
     * @throws Exception
     */
    public static void printAllOrders(ArrayList<Order> orders) throws Exception
    {
           for (Order order : orders)
            {
                SystemReporter.report("Order info: ", new String[] {
                        "Order ID:\t" + order.getRunId(),
                        "For user:\t" + order.getUser().getId(),
                        "Order date:\t" + order.getOrderDate(),
                        "Order deliviry date:\t" + order.getDeliveryDate().toString(),
                        "Order deliviry type:\t" + order.getDeliveryType().toString(),
                        "Order state:\t" + order.getState().toString()
                    });
           }
    }

    public static void printOrdersByState(ArrayList<Order> orders, StateType state) throws Exception
    {
        for (Order order : orders)
        {
            if (order.getState() == state)
            {
                SystemReporter.report("Printing order with this state " + state.toString() + ": ", new String[] {
                        "Order ID:\t" + order.getRunId(),
                        "For user:\t" + order.getUser().getId(),
                        "Order date:\t" + order.getOrderDate(),
                        "Order deliviry date:\t" + order.getDeliveryDate().toString(),
                        "Order deliviry type:\t" + order.getDeliveryType().toString()
                    });
            }
        }
    }

    public static void printAllProducts(ArrayList<Category> categories) throws Exception
    {
        for (Category category : categories)
        {
            ArrayList<Product> products =  category.getProductsList();
            for (Product product : products)
            {
                printProduct(product);
            }
        }
    }

    //TODO: print sorted products

    public static void printProductsByCategory(Category.CategoryType categoryType, ArrayList<Category> categories) throws Exception
    {
        for (Category category : categories)
        {
            if (category.getName() == categoryType)
            {
                ArrayList<Product> products =  category.getProductsList();
                for (Product product : products)
                {
                    printProduct(product);
                }
                continue;
            }
        }
    }

    public static void printAllCategiries(ArrayList<Category> categories) throws Exception
    {
        for (Category category : categories)
        {
            SystemReporter.report("Products category : " + category.getName() + " info: ", new String[] {
                "Categiry id:\t" + category.getRunId(),
                "Category name:\t" + category.getName()
                            });
        }
    }

    /**
     * Printing product amount for all categories
     * @param categories = category list
     * @throws Exception
     */
    public static void printProductsAmountByCategory(ArrayList<Category> categories) throws Exception
    {
        for (Category category : categories)
        {
            SystemReporter.report(
                    "Products amount for category : " + category.getName() + " is: " + category.getProductsList().size());
        }
    }

    public static void printProductsByPrice(LergerSmaller by, double price, ArrayList<Category> categories) throws Exception
    {
       for (Category category : categories)
        {
            ArrayList<Product> products =  category.getProductsList();
            for (Product product : products)
            {
               switch(by)
               {
                   case Larger:
                       if (product.getPrice() > price)
                           printProduct(product);
                       break;
                   case Smaller:
                      if (product.getPrice() <= price)
                           printProduct(product);
                       break;
                   default:
                       break;
               }
            }
        }

    }
    
    public enum LergerSmaller
    {
        Larger , Smaller
    }
    /**
     * Print product helper
     * @param product
     * @throws Exception
     */
    private static void printProduct(Product product) throws Exception
    {
           SystemReporter.report("Product info: ", new String[] {
                "Product name:\t" + product.getName(),
                "Product SN:\t" + product.getSerialNumber(),
                "Product price:\t" + product.getPrice(),
                "Product quantity:\t" + product.getQuantity(),
                "Product type:\t" + product.getType().toString()
            });
    }





    


}
