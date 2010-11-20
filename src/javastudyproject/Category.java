
package javastudyproject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author AlonPisnoy
 */
public class Category implements Serializable {

    private static int runid;
    private int id;
    private String  name;
    
    //constructor
    public Category(String  name)
    {
        runid++;
        id = runid;
        this.name = name;
    }

    public int getRunId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }


}
