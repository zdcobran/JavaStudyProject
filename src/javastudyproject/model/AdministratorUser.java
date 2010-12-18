/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author EladYarkoni
 */
@Entity
public class AdministratorUser extends User implements Serializable {

    public AdministratorUser()
    {}

    public AdministratorUser(
            String userName,
            String id,
            String firstName, 
            String lastName,
            String email,
            String password,
            String age
            )
    {
        this.userName = userName;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.createDate = new Date(System.currentTimeMillis());
    }



    @Override
    public String toString()
    {
        return "AdministratorUser";
    }

}
