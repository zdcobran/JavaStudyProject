package javastudyproject;

import javastudyproject.reporting.SystemReporter;
import javastudyproject.users.User;

/**
 * This class providing orders management
 * @author alon
 */
public class OrederOps extends ObjectSystem{

    public static void updateOrderStatus(int orderRunId, Order.StateType status) throws Exception
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
    
    public static void deleteOrder(int orderRunId) throws Exception
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
       for (Order order: orders)
       {
           printOrderImpl(order);
       }
    }

    public static void printOrdersByState(Order.StateType state) throws Exception
    {
        SystemReporter.report("Printing all orders with this state: " + state);
       for (Order order: orders)
       {
           if (order.getState().equals(state))
           {
               printOrderImpl(order);
           }
       }
    }

    public static void printOrdersUserNamesByPurchasedProduct(Product productToFind) throws Exception
    {
        SystemReporter.report("Finding all user that purchased " + productToFind.getName());
        for (Order order : orders)
        {
            for(Product product: order.getProducts())
            {
                if(product == productToFind)
                {
                    SystemReporter.report("The user: " + order.getUser().getUserName() + " Purchased this product.");
                    break;
                }
            }
        }
    }

    private static void printOrderImpl(Order order) throws Exception
    {
        SystemReporter.report("Order info for user " + order.getUser().getUserName() + ": ", new String[] {
            "Order ID:\t" + order.getRunId(),
            "Order date:\t" + order.getOrderDate(),
            "Order deliviry date:\t" + order.getDeliveryDate().toString(),
            "Order deliviry type:\t" + order.getDeliveryType().toString(),
            "Order state:\t" + order.getState().toString()
        });
    }
}
