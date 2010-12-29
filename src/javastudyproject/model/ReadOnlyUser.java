/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 * Read only user class
 * @author EladYarkoni
 */
@Entity
public class ReadOnlyUser extends User implements Serializable{

    private String owningUserName;

    //@MapsId @OneToOne
    private int readOnlyUserOrderId;

    public ReadOnlyUser() {};

    public ReadOnlyUser(
            String userName,
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String age,
            String owningUserName,
            int readOnlyUserOrderId
            )
    {

        this.userName = userName;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.owningUserName = owningUserName;
        this.readOnlyUserOrderId = readOnlyUserOrderId;
        this.createDate = new Date(System.currentTimeMillis());
    }

    public String getOwningUserName()
    {
        return owningUserName;
    }

    public void setOwningUserName(String owningUserName)
    {
        this.owningUserName = owningUserName;
    }

    public int getReadOnlyUserOrderId()
    {
        return readOnlyUserOrderId;
    }

    public void setReadOnlyUserOrderId(int readOnlyUserOrderId)
    {
        this.readOnlyUserOrderId = readOnlyUserOrderId;
    }

    @Override
    public String toString()
    {
        return "ReadOnlyUser";
    }
}
