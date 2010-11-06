/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import java.util.ArrayList;

/**
 *
 * @author EladYarkoni
 */
public class Category {

    private static int runid;
    private CategoryType name;
    private ArrayList<Product> productList;
    public static enum CategoryType {Electronics, Car, Office, Home}
    
}
