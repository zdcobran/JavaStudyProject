/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.reporting;

/**
 * @author AlonPisnoy
 * This is a reporter class that prints to console and throws
 * Exceptions with messages.
 */
public class Reporter {

    public static void report(String message) throws Exception
    {
        report(message, false);
    }

    public static void report(String message,  boolean throwException) throws Exception
    {
        if (throwException)
        {
            throw new Exception(message);
        }
        else
        {
            System.out.println(message);
        }
    }




    
}
