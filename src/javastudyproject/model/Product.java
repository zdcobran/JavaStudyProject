/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Alon pisnoy
 */
@Entity
@Table(name="products")
public class Product implements Serializable{
    @Id
    private String name;
    @OneToOne
    private Category category;
    private String serialNum;
    private double price;
    private int quantity;

    //constructor
    public Product()
    {
        //Empty constructor
    }

    public Product(String name, Category category,String serialNum, double price, int quantity)
    {
        this.name = name;
        this.category = category;
        this.serialNum = serialNum;
        this.price = price;
        this.quantity = quantity;
    }

    //Copy contructor
    public Product(Product another)
    {
        this.name = another.name;
        this.category = another.category;
        this.serialNum = another.serialNum;
        this.price = another.price;
        this.quantity = another.quantity;
    }

    public String getName()
    {
        return name;
    }

    public String getSerialNumber()
    {
        return serialNum;
    }

    public Category getCategory()
    {
        return category;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSerialNumber(String serialNum)
    {
        this.serialNum = serialNum;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Product setQuantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public void setCategory(Category category)
    {
        this.category  = category;
    }
}
