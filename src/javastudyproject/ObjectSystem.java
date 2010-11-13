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

    protected ArrayList<User> users;

    public ObjectSystem() {
       users = FilesDB.ReadUsers();
    }

    public void UpdateUsers() {
        FilesDB.UpdateUsers(this.users);
    }

    public User.UserType authenticate(String user, String password) {
        return User.UserType.ReadOnlyUser;
    }


    
}
