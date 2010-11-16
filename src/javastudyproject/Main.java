/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import javastudyproject.menus.textual.*;
import javastudyproject.reporting.SystemReporter;



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
        ObjectSystem objSystem = new ObjectSystem();
        objSystem.initialize();

        try
        {
             new LoginScreen("ERProduct","0.1 alpha");
        }
        catch(Exception e)
        {
            System.out.println("Catched an exeption: " + e.getMessage());
        }

       
    }


}
