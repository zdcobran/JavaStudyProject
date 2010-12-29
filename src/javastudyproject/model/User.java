/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Users class
 * Using fluent interface with allows us to update the object fluently
 * @author Alon Pisnoy
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="users")
public class User implements Serializable  {

    @Id
    protected String userName;
    protected String id;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String password;
    @Temporal(TemporalType.DATE)
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

    public boolean isNull()
    {
        return this == null;
    }
}
