/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javastudyproject.Category;
import javastudyproject.Order;
import javastudyproject.Product;
import javastudyproject.users.AdministratorUser;
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
    private static final String DEFAULT_ADMIN = "admin";
    private static final String DEFAULT_ADMIN_PASS = "123456";
    private static final String DEFAULT_CATEGORY_ELECTRONICS = "Electronics";
    private static final String DEFAULT_CATEGORY_CAR = "Car";
    private static final String DEFAULT_CATEGORY_HOME = "Home";
    private static final String DEFAULT_CATEGORY_OFFICE = "Office";


    public static ArrayList<User> ReadUsers() {
        try {
            ObjectInputStream istream = new ObjectInputStream(new FileInputStream(USERSFILE));
            return (ArrayList<User>) istream.readObject();
        }
        catch (IOException ex) {
            AdministratorUser admin = new AdministratorUser();
            ArrayList<User> usersList = new ArrayList<User>();
            usersList.add(admin.setUserName(DEFAULT_ADMIN).setPassword(DEFAULT_ADMIN_PASS).setEmail("root@email.com").setId("123456"));
            return usersList;
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
            ArrayList<Category> categoryList = new ArrayList<Category>();
            categoryList.add(new Category(DEFAULT_CATEGORY_CAR));
            categoryList.add(new Category(DEFAULT_CATEGORY_ELECTRONICS));
            categoryList.add(new Category(DEFAULT_CATEGORY_HOME));
            categoryList.add(new Category(DEFAULT_CATEGORY_OFFICE));
           return categoryList;
        }
        catch(ClassNotFoundException exc) {}
        return null;
    }


    public static void UpdateCategories(ArrayList<Category> categories) {

        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(CATEGORYFILE));
            ostream.writeObject(categories);
        }
        catch (IOException ex){}
    }

    public static void UpdateOrders(ArrayList<Order> orders) {

        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(ORDERFILE));
            ostream.writeObject(orders);
        }
        catch (IOException ex) {}
    }

}
