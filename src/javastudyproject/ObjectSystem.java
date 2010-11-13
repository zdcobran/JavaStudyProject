/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import java.util.ArrayList;
import javastudyproject.db.FilesDB;
import javastudyproject.users.ReadOnlyUser;
import javastudyproject.users.User;

/**
 *
 * @author EladYarkoni
 */
public class ObjectSystem {

    protected static ArrayList<User> users;
    protected static ArrayList<Product> products;
    protected static ArrayList<Category> categories;


    public ObjectSystem() {
       users = FilesDB.ReadUsers();
       products = FilesDB.ReadProducts();
       categories = FilesDB.ReadCategories();
    }



    
}
