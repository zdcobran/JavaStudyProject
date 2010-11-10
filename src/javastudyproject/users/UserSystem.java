/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.users;

import java.io.Serializable;
import java.util.ArrayList;
import javastudyproject.db.FilesDB;

/**
 *
 * @author EladYarkoni
 */
public class UserSystem implements Serializable {

    private ArrayList<User> users;

    public  UserSystem() {

         this.UserSystem(FilesDB.ReadUserSystem());
    }

    public  void UserSystem(UserSystem us) {

        try {
            this.users = us.users;
        }
         catch (NullPointerException ex) {
            this.users = new ArrayList<User>();
         }
    }

    public void AddReadOnlyUser(ReadOnlyUser user) {
        users.add(user);
    }

    public UserType Authentication(String user, String password) throws LoginException {
        // code for authenticate
        

        return  UserType.ReadWriteUser;
    }

    public void Update() {
        FilesDB.UpdateUsers(this);
        this.UserSystem(FilesDB.ReadUserSystem());
    }

    public static enum UserType
    {
        Administrator, ReadOnlyUser, ReadWriteUser;
    }

}
