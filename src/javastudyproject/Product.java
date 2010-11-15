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
    private String serialNum;
    private double price;
    private int quantity;
    private Category.CategoryType type;

    //constructor
    public Product(String name, String serialNum, double price, int quantity, Category.CategoryType type)
    {
        this.name = name;
        this.serialNum = serialNum;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public String getSerialNumber()
    {
        return serialNum;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public Category.CategoryType getType()
    {
        return type;
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

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setType(Category.CategoryType type)
    {
        this.type = type;
    }
}
