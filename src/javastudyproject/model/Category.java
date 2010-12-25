
package javastudyproject.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author AlonPisnoy
 */
@Entity
@Table(name="categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String  name;

    public Category()
    {
        //empty contructor
    }
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
