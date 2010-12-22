/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.client;
import javastudyproject.service.ServiceSystem;



/**
 *
 * @author EladYarkoni
 */
public class Main {
    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {

        //Initialize the object system class that init all collections
        ServiceSystem objSystem = new ServiceSystem();

        try
        {
             new LoginScreen();
        }
        catch(Exception e)
        {
            System.out.println("Catched an exeption: " + e.getMessage());
            objSystem.cleanup();
        }
        objSystem.cleanup();
    }


}
