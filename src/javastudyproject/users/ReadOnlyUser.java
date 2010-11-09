/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.users;

/**
 *
 * @author EladYarkoni
 */
public class ReadOnlyUser extends User {

    public ReadOnlyUser(
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String age
            )
    {
        runId++;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
    }
}
