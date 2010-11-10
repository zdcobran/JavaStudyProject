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
import javastudyproject.users.User;
import javastudyproject.users.UserSystem;

/**
 *
 * @author EladYarkoni
 */
public class FilesDB {

    private static final String USERSFILE = "userSystem.db";

    public static UserSystem ReadUserSystem() {

        try {
            ObjectInputStream istream = new ObjectInputStream(new FileInputStream(USERSFILE));
            return (UserSystem) istream.readObject();
        }
        catch (IOException ex) {}
        catch(ClassNotFoundException exc) {}
        return null;
    }

    public static void UpdateUsers(UserSystem userSystem) {

        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(USERSFILE));
            ostream.writeObject(userSystem);
        }
        catch (IOException ex) {}
    }
    

}
