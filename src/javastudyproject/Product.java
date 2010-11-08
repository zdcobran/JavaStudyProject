/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

/**
 *
 * @author EladYarkoni
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
}
