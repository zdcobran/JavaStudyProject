/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.users;

import java.util.Date;

/**
 *
 * @author EladYarkoni
 */
public class User {

    protected static int runId;
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected Date createDate;
    protected String age;
    // todo: list of orders

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


}
