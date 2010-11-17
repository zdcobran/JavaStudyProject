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
 * Users class
 * Using fluent interface with allows us to update the object fluently
 * @author Alon Pisnoy
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

    public User setUserName(String userName)
    {
        this.userName = userName;
        return this;
    }

    public User setId(String id)
    {
        this.id = id;
        return this;
    }

    public User setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public User setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public User setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public User setAge(String age)
    {
        this.age = age;
        return this;
    }
}
