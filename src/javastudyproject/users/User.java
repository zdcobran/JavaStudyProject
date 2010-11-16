/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javastudyproject.Order;

/**
 *
 * @author EladYarkoni
 */
public class User implements Serializable  {

    protected static int runId;
    protected int userRunId;
    protected String userName;
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected Date createDate;
    protected String age;


    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public String getAge()
    {
        return age;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public void setAge(String age)
    {
        this.age = age;
    }
}
