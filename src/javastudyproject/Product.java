/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

/**
 *
 * @author Alon pisnoy
 */
public class Product {

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
}
