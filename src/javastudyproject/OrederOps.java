package javastudyproject;

import javastudyproject.reporting.SystemReporter;
import javastudyproject.users.User;

/**
 * This class providing orders management
 * @author alon
 */
public class OrederOps extends ObjectSystem{

    public static void updateOrderStatus(User user, int orderRunId, Order.StateType status) throws Exception
    {
        for (Order order: orders)
        {
            if (order.getRunId() == orderRunId)
            {
                order.updateState(status);
                SystemReporter.report("Updating order: " + orderRunId + " status to: " + status);
                return;
            }
        }
        SystemReporter.report("Didn't found order id: " + orderRunId, true);
    }
    
    public static void deleteOrder(User user, int orderRunId) throws Exception
    {
        for (Order order: orders)
        {
            if (order.getRunId() == orderRunId)
            {
                SystemReporter.report("Deleting order with this id: " + orderRunId) ;
                orders.remove(order);
                return;
            }
        }
        SystemReporter.report("Didn't found order id: " + orderRunId, true);
    }

    public static void printAllOrders() throws Exception
    {
        
    }

    public static void printOrdersByState(Order.StateType state) throws Exception
    {

    }
}
