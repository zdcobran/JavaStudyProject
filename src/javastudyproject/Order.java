/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import java.util.ArrayList;
import java.util.Date;
import javastudyproject.users.User;

/**
 *
 * @author EladYarkoni
 */
public class Order {

    private static int runid;
    private User user;
    private Date orderDate;
    private Date deliveryDate;
    private double totalPrice;
    private ArrayList<Product> products;
    private DeliveryType delivery;
    private StateType state;
    
    public Order(User user, Date deliveryDate, DeliveryType delivery)
    {
    
    }





   public enum DeliveryType {Self, Shipping};
   public enum StateType {New, Pending, Ready, InProgress, Finished};
}
