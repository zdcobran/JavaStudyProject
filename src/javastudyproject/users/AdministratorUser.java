/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.users;

import java.io.Serializable;

/**
 *
 * @author EladYarkoni
 */
public class AdministratorUser extends User implements Serializable {
    
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
        runId++;
        this.userRunId = runId;
        this.userName = userName;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "AdministratorUser";
    }

}
