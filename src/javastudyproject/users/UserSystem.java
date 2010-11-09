/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.users;

import java.util.ArrayList;
import javastudyproject.db.FilesDB;

/**
 *
 * @author EladYarkoni
 */
public class UserSystem {

    private ArrayList<User> users;

    public void UserSystem() {

        this.users = FilesDB.ReadUsers();
    }

    public String Authentication(String user, String password) throws LoginException {
        // code for authenticate
        

        return "AdministratorUser";
    }

}
