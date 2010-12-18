
package javastudyproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author AlonPisnoy
 */

public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String  name;
    
    //constructor
    public Category(String  name)
    {
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
