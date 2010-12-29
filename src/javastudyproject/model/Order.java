/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author AlonPisnoy
 */
@Entity
@Table(name="orders")
public class Order implements Serializable{

    @OneToOne
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    private double totalPrice;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "joined_orders_products",
    joinColumns = {@JoinColumn(name = "order_id")},
    inverseJoinColumns = {@JoinColumn(name = "product_name")})
    private  List<Product> orderProducts;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    @Enumerated(EnumType.STRING)
    private StateType state;

    //constructors
    public Order() {}

    public Order(User user, Date deliveryDate, DeliveryType deliveryType)
    {
        orderProducts = new ArrayList<Product>();
        this.user = user;
        orderDate = new Date(System.currentTimeMillis());
        this.deliveryDate = deliveryDate;
        this.deliveryType = deliveryType;
        state = StateType.New;
    }

    /**
     * The return type is order is for fluent interface to add multiple
     * products in one line
     * @param product
     * @return
     */
    public Order addProduct(Product product)
    {
        orderProducts.add(product);
        return this;
    }

    public Order addMultipleProducts(List<Product> productsList)
    {
        for (Product product: productsList)
        {
            orderProducts.add(product);
        }
        return this;
    }

    public List<Product> getProducts()
    {
         return (List<Product>)orderProducts;
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
