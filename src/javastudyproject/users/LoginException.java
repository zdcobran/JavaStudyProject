/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.users;

/**
 *
 * @author EladYarkoni
 */
public class LoginException extends Exception {

    private String message;

    public LoginException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
