/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javastudyproject.Category;
import javastudyproject.Order;
import javastudyproject.Product;
import javastudyproject.users.User;

/**
 *
 * @author EladYarkoni
 */
public class FilesDB {

    private static final String USERSFILE = "users.db";
    private static final String PRODUCTSFILE = "products.db";
    private static final String CATEGORYFILE = "categories.db";
    private static final String ORDERFILE = "orders.db";

    public static ArrayList<User> ReadUsers() {
        try {
            ObjectInputStream istream = new ObjectInputStream(new FileInputStream(USERSFILE));
            return (ArrayList<User>) istream.readObject();
        }
        catch (IOException ex) {
            return new ArrayList<User>();
        }
        catch(ClassNotFoundException exc) {}
        return null;
    }


    public static void UpdateUsers(ArrayList<User> users) {

        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(USERSFILE));
            ostream.writeObject(users);
        }
        catch (IOException ex) {}
    }
    
        public static ArrayList<Product> ReadProducts() {
        try {
            ObjectInputStream istream = new ObjectInputStream(new FileInputStream(PRODUCTSFILE));
            return (ArrayList<Product>) istream.readObject();
        }
        catch (IOException ex) {
            return new ArrayList<Product>();
        }
        catch(ClassNotFoundException exc) {}
        return null;
    }

        public static ArrayList<Order> ReadOrders() {
        try {
            ObjectInputStream istream = new ObjectInputStream(new FileInputStream(ORDERFILE));
            return (ArrayList<Order>) istream.readObject();
        }
        catch (IOException ex) {
            return new ArrayList<Order>();
        }
        catch(ClassNotFoundException exc) {}
        return null;
    }


    public static void UpdateProducts(ArrayList<Product> products) {

        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(PRODUCTSFILE));
            ostream.writeObject(products);
        }
        catch (IOException ex) {}
    }
    
        public static ArrayList<Category> ReadCategories() {
        try {
            ObjectInputStream istream = new ObjectInputStream(new FileInputStream(CATEGORYFILE));
            return (ArrayList<Category>) istream.readObject();
        }
        catch (IOException ex) {
            return new ArrayList<Category>();
        }
        catch(ClassNotFoundException exc) {}
        return null;
    }


    public static void UpdateCategories(ArrayList<Category> categories) {

        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(CATEGORYFILE));
            ostream.writeObject(categories);
        }
        catch (IOException ex) {}
    }

    public static void UpdateOrders(ArrayList<Order> orders) {

        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(ORDERFILE));
            ostream.writeObject(orders);
        }
        catch (IOException ex) {}
    }

}
