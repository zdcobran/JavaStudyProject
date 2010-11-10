/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author eyarkoni
 */
public class AdministratorScreen {

    private BufferedReader reader;

    public AdministratorScreen() {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Administrative menu\n-----------------------------------------------");
        System.out.println("1. Products Management");
        System.out.println("2. Customers  Management");
        System.out.println("3. Orders  Management");
        System.out.println("4. Reports\n");

        System.out.print("Your choise: ");

        try {
            int choise=  reader.read();
        } catch (IOException ex) {}
    }





}
