/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import java.util.ArrayList;
import javastudyproject.db.FilesDB;
import javastudyproject.users.User;

/**
 *
 * @author EladYarkoni
 */
public class ObjectSystem {

    protected static ArrayList<User> users;
    protected static ArrayList<Product> products;
    protected static ArrayList<Category> categories;
     protected static ArrayList<Order> orders;

     public ObjectSystem () { }

    public void initialize()
    {
        users = FilesDB.ReadUsers();
        products = FilesDB.ReadProducts();
        categories = FilesDB.ReadCategories();
        orders = FilesDB.ReadOrders();
    }

    public void saveUsers()
    {
        FilesDB.UpdateUsers(users);
    }

     public void saveProducts()
    {
        FilesDB.UpdateProducts(products);
    }

     public void saveCategories()
    {
       FilesDB.UpdateCategories(categories);
    }

     public void saveOrders()
    {
       FilesDB.UpdateOrders(orders);
    }

}
