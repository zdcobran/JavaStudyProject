/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import java.util.ArrayList;

/**
 *
 * @author AlonPisnoy
 */
public class Category {

    private static int runid;
    private CategoryType name;
    private ArrayList<Product> productList;
    
    //constructor
    public Category(CategoryType name)
    {
        runid++;
        this.name = name;
    }

    public Category addProduct(Product product)
    {
        productList.add(product);
        return this;
    }

    public int getRunId()
    {
        return runid;
    }

    public ArrayList<Product> getProductsList()
    {
        return productList;
    }

    public CategoryType getName()
    {
        return name;
    }
    
    public static enum CategoryType
    {
        Electronics, Car, Office, Home
    }

}
