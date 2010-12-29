package javastudyproject.reporter;

/**
 * @author AlonPisnoy
 * This is a reporter class that prints to console and throws
 * Exceptions with messages.
 */
public class SystemReporter {

    public static void report(String message) throws Exception
    {
        report(message, false);
    }

    public static void report(String title, String[] messages) throws Exception
    {
        report(title);
        for (String message : messages)
        {
            report(message);
        }
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
