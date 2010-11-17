/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javastudyproject.users.User;

/**
 *
 * @author AlonPisnoy
 */
public class Order implements Serializable{

    private static int runid;
    private User user;
    private int id;
    private Date orderDate;
    private Date deliveryDate;
    private double totalPrice;
    private ArrayList<Product> products;
    private DeliveryType deliveryType;
    private StateType state;

    //constructor
    public Order(User user, Date deliveryDate, DeliveryType deliveryType)
    {
        runid++;
        this.id = runid;
        this.user = user;
        orderDate = new Date(System.currentTimeMillis());
        this.deliveryDate = deliveryDate;
        this.deliveryType = deliveryType;
        state = StateType.New;
    }

    /**
     * The return type is order is for fluent interfase to add multiple
     * products in one line
     * @param product
     * @return
     */
    public Order addProduct(Product product)
    {
        products.add(product);
        return this;
    }

    public ArrayList<Product> getProducts()
    {
         return products;
    }

    public void updateState(StateType state)
    {
        if (this.state != state) this.state = state;
    }

    public void updateTotalPrice(double price)
    {
        totalPrice += price;
    }

    public int getRunId()
    {
        return id;
    }

    public User getUser()
    {
        return user;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    public DeliveryType getDeliveryType()
    {
        return deliveryType;
    }

    public StateType getState()
    {
        return state;
    }

   public enum DeliveryType {Self, Shipping, None};
   public enum StateType {New, Pending, Ready, InProgress, Finished};
}
