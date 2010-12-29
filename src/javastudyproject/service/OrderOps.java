/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.service;

import java.util.List;
import javastudyproject.model.Order;
import javastudyproject.model.Product;

/**
 *
 * @author alon
 */
public interface OrderOps {

    public void updateOrderStatus(int orderRunId, Order.StateType status) throws Exception;
    public void deleteOrder(int orderRunId) throws Exception;
    public void printAllOrders() throws Exception;
    public void printOrdersByState(Order.StateType state) throws Exception;
    public void printOrdersUserNamesByPurchasedProduct(Product productToFind) throws Exception;
    public List<Order> getAllOrders();
    public void addNewOrder(Order order) throws Exception;

}
