/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import java.io.Serializable;

/**
 *
 * @author Alon pisnoy
 */
public class Product implements Serializable{

    private String name;
    private Category category;
    private String serialNum;
    private double price;
    private int quantity;

    //constructor
    public Product()
    {
        
    }

    public Product(String name, Category category,String serialNum, double price, int quantity)
    {
        this.name = name;
        this.category = category;
        this.serialNum = serialNum;
        this.price = price;
        this.quantity = quantity;
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
