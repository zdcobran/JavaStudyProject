/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.reporting;
import java.util.ArrayList;
import javastudyproject.Order;
import javastudyproject.users.User;

/**
 *
 * @author alon
 */
public class InternalReporter {

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

}
