/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javastudyproject.service.ServiceSystem;
import javastudyproject.reporting.SystemReporter;

/**
 * The Administrator main menu
 * @author eyarkoni
 */
public class AdministratorScreen extends ServiceSystem {

    private BufferedReader reader;

   
    public AdministratorScreen() throws Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Administrative menu\n-----------------------------------------------");
        System.out.println("1. Products Management");
        System.out.println("2. Customers  Management");
        System.out.println("3. Orders  Management");
        System.out.println("4. Reports\n");
        System.out.println("5. Save and exit\n");

        System.out.print("Your choise: ");

        try {
           int choise=  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1: { new ProductManagementScreen(); }break;
                case 2: { new CustomersScreen(); }break;
                case 3: { new ServiceSystem(); }break;
                case 4: { new ReportsScreen(); }break;
                case 5: {
                    new LoginScreen();
                }break;
            }
            new AdministratorScreen();
        } 
        catch(Exception e)
        {
            SystemReporter.report("Failed to perform last action, error: " + e.getMessage());
            new AdministratorScreen();
        }
    }





}
