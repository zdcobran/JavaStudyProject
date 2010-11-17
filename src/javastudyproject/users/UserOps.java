/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.users;

import java.util.ArrayList;
import javastudyproject.ObjectSystem;
import javastudyproject.Order;
import javastudyproject.db.FilesDB;
import javastudyproject.reporting.SystemReporter;

/**
 * This class providing user management
 * @author alon
 */
public class UserOps extends ObjectSystem{

    public static void UpdateUsers() {
        FilesDB.UpdateUsers(users);
    }

    public static User authenticate(String userName, String password) throws Exception {
        ArrayList<User> authUser = new ArrayList<User>();
        try
        {
            authUser = getUserByGivenCriteria(UserCriteria.UserName, new User().setUserName(userName));
        }
        catch(Exception e)
        {
            SystemReporter.report("Wrong user name provided", true);
        }
        if (authUser.get(0).getPassword().equals(password))
            return authUser.get(0);
        else
        {
            SystemReporter.report("Wrong password provided", true);
        }
        return null;
    }

     /**
     * Adding new user
     * Verifying that all unique details are unique
     * @param name
     * @param serialNum
     * @param price
     * @param quantity
     * @param type
     * @throws Exception
     */
    public static void addNewUser(
            UserType type,
            String userName,
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String age) throws Exception
    {
        addNewUser(type, userName,id, firstName, lastName,email,password,age, null, 0);
    }

    public static void addNewUser(
            UserType type,
            String userName,
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String age,
            String ownningUserName,
            int readOnlyUserOrderId) throws Exception
    {
        //validating that the new user name is unique
        for (User user : users)
        {
             if (user.getUserName().equals(userName))
             {
                SystemReporter.report("The provided user name: " + userName + " is already exists in the system", true);
             }
        }
        //validating that the new user ID is unique
        for (User user : users)
        {
             if (user.getId().equals(id))
             {
                SystemReporter.report("The provided user id: " + id + " is already exists in the system", true);
             }
        }

       //validating that the new user email is unique
        for (User user : users)
        {
             if (user.getEmail().equals(email))
             {
                SystemReporter.report("The provided user email: " + email + " is already exists in the system", true);
             }
        }
        switch(type)
        {
            case Administrator:
                users.add(new AdministratorUser(userName, id, firstName, lastName, email, password, age));
                break;
            case ReadWriteUser:
                users.add(new ReadWriteUser(userName, id, firstName, lastName, email, password, age));
                break;
            case ReadOnlyUser:
                if(ownningUserName == null)
                    SystemReporter.report("Owning user name was not provided", true);
                boolean found = false;
                if (!found)
                    SystemReporter.report("The provided owning user or id was not found", true);
                users.add(new ReadOnlyUser(userName, id, firstName, lastName, email, password, age, ownningUserName, readOnlyUserOrderId));
                break;
        }
        SystemReporter.report("User created successfully");
    }

    /**
     * Updating the user detail by given user name
     * @param criteria
     * @param name
     * @param productContainer
     * @throws Exception
     */
    public static void updateUserDetailsByUserName(UserCriteria criteria, String userName, User userContainer) throws Exception
    {
        for (User user : users)
        {
            if (user.getUserName().equals(userName))
            {
                switch(criteria)
                {
                    case UserName:
                        //validating that the new user name is unique
                        for (User userUnique: users)
                        {
                             if (userUnique.getUserName().equals(userContainer.getUserName()))
                             {
                                SystemReporter.report(
                                        "The provided user name: "
                                        + userContainer.getUserName() + " is already exists in the system", true);
                             }
                        }

                        user.setUserName(userContainer.getUserName());
                        SystemReporter.report("Updated user name to: " + userContainer.getUserName());
                        return;
                    case Id:
                        //validating that the new id is unique
                        for (User userUnique: users)
                        {
                             if (userUnique.getId().equals(userContainer.getId()))
                             {
                                SystemReporter.report(
                                        "The provided ID: "
                                        + userContainer.getId() + " is already exists in the system", true);
                             }
                        }

                        user.setId(userContainer.getId());
                        SystemReporter.report(
                                "Updated user id to: " + userContainer.getId());
                        return;
                    case FirstName:
                        user.setFirstName(userContainer.getFirstName());
                        SystemReporter.report(
                                "Updated users first name to: " + userContainer.getFirstName());
                        return;
                    case LastName:
                        user.setLastName(userContainer.getLastName());
                        SystemReporter.report(
                                "Updated users last name to: " + userContainer.getLastName());
                        return;
                    case Email:
                       //validating that the new id is unique
                        for (User userUnique: users)
                        {
                             if (userUnique.getEmail().equals(userContainer.getEmail()))
                             {
                                SystemReporter.report(
                                        "The provided Email: "
                                        + userContainer.getEmail() + " is already exists in the system", true);
                             }
                        }

                        user.setEmail(userContainer.getEmail());
                        SystemReporter.report(
                                "Updated users email to: " + userContainer.getEmail());
                        return;
                    case Age:
                        user.setAge(userContainer.getAge());
                        SystemReporter.report(
                                "Updated users age to: " + userContainer.getAge());
                        return;

                    default:
                        SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
                }
            }
        }
        SystemReporter.report("Didn't find user with user name: " + userName, true);
    }

    /**
     * Return the product list that reply to the given criteria
     * the product container will contain the search value
     * @param criteria
     * @param productContainer
     * @return
     * @throws Exception
     */
    public static ArrayList<User> getUserByGivenCriteria(UserCriteria criteria, User userContainer) throws Exception
    {
        ArrayList<User> returnList = new ArrayList<User>();
        for (User user: users)
        {
            switch(criteria)
            {
                case FirstName:
                    if (user.getFirstName().equals(userContainer.getFirstName()))
                    {
                        returnList.add(user);
                    }
                    break;
                case LastName:
                   if (user.getLastName().equals(userContainer.getLastName()))
                    {
                        returnList.add(user);
                    }
                    break;
                case UserName:
                   if (user.getUserName().equals(userContainer.getUserName()))
                    {
                        returnList.add(user);
                        return returnList;

                    }
                    break;
                case Id:
                    if (user.getId().equals(userContainer.getId()))
                    {
                        returnList.add(user);
                        return returnList;
                    }
                    break;
                case Email:
                    if (user.getEmail().equals(userContainer.getEmail()))
                    {
                        returnList.add(user);
                        return returnList;
                    }
                    break;
                default:
                    SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
            }
        }
        if (returnList.isEmpty())
            SystemReporter.report("Didn't find users for given search", true);
        return returnList;
    }

    /**
     * Prints user info by given name
     * @param name
     * @throws Exception
     */
    public static void printUserInfo(String userName) throws Exception
    {
        User userContainer = new User();
        userContainer.setUserName(userName);
        ArrayList<User> returnList = getUserByGivenCriteria(UserCriteria.UserName, userContainer);
        printUserInfoImpl(returnList.get(0));
    }

    /**
     * Delete user by given name
     * @param name
     * @throws Exception
     */
    public static void deleteUser(String userName) throws Exception
    {
        for (User user: users)
        {
            if (user.getUserName().equals(userName))
            {
                SystemReporter.report("Deleting user with this info: ");
                printUserInfoImpl(user);
                users.remove(user);
                return;
            }
        }
        SystemReporter.report("Didn't find user with given name", true);
    }

    /**
     * Prints user info need to provide user class
     * @param product
     * @throws Exception
     */
    private static  void printUserInfoImpl(User user) throws Exception
    {
              SystemReporter.report("User info:", new String[] {
                    "User name: " + user.getUserName(),
                    "User id: " + user.getId(),
                    "User first name: " + user.getFirstName(),
                    "User last name: " + user.getLastName(),
                    "User email: " + user.getEmail(),
                    "User age: " + user.getAge(),
                    "Create date: " + user.getCreateDate(),
              });
    }

    public static void printAllUsers() throws Exception
    {
        for (User user: users)
        {
            printUserInfoImpl(user);
        }
    }

    public enum UserCriteria
    {
        UserName, Id, FirstName, LastName, Password, Email, Age, CreateDate
    }

    public static enum UserType
    {
        Administrator, ReadOnlyUser, ReadWriteUser;
    }
}
