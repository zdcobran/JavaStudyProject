/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.reporting;
import java.util.ArrayList;
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

    public static void printAllProducts(ArrayList<Product> products) throws Exception
    {
        for (Product product : products)
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

    


}
