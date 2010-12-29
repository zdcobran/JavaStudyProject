/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.service;

import java.util.ArrayList;
import java.util.List;
import javastudyproject.model.AdministratorUser;
import javastudyproject.model.ReadOnlyUser;
import javastudyproject.model.ReadWriteUser;
import javastudyproject.model.User;
import javastudyproject.reporter.SystemReporter;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * This class providing user management
 * @author alon
 */
public class UserOpsBean implements UserOps{


    private EntityManager em;

    public UserOpsBean(EntityManager entityManager) {
        em = entityManager;
    }

    public User authenticate(String userName, String password) throws Exception {

        User user = null;
        try
        {
            user = em.find(User.class, userName);
        }
        catch(Exception e)
        {
            SystemReporter.report("Wrong user name provided", true);
            throw e;
        }
        if (user.getPassword().equals(password))
            return user;
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
    public void addNewUser(
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

    public void addNewUser(
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
         if (em.find(User.class, userName) != null)
         {
            SystemReporter.report("The provided user name: " + userName + " is already exists in the system", true);
         }
        //validating that the new user ID is unique
        if (em.createQuery("select u from User u where u.id = '" + id +"'" ).getFirstResult() == 0)
        {
            SystemReporter.report("The provided user id: " + id + " is already exists in the system", true);
        }

       //validating that the new user email is unique
       if (em.createQuery("select u from User u where u.email = '" + email+"'" ).getFirstResult() == 0)
        {
            SystemReporter.report("The provided user email: " + email + " is already exists in the system", true);
        }
        try
        {
            switch(type)
            {
                case Administrator:
                    em.getTransaction().begin();
                    em.persist(new AdministratorUser(userName, id, firstName, lastName, email, password, age));
                    em.flush();
                    em.getTransaction().commit();
                    break;
                case ReadWriteUser:
                    em.getTransaction().begin();
                    em.persist(new ReadWriteUser(userName, id, firstName, lastName, email, password, age));
                    em.flush();
                    em.getTransaction().commit();
                    break;
                case ReadOnlyUser:
                    em.getTransaction().begin();
                    em.persist(new ReadOnlyUser(userName, id, firstName, lastName, email, password, age, ownningUserName, readOnlyUserOrderId));
                    em.flush();
                    em.getTransaction().commit();
                    break;
            }
        }
        catch(Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            SystemReporter.report(
                    "Catched exception when performed write to DB: " + e.getMessage(), true);
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
    public void updateUserDetailsByUserName(UserCriteria criteria, String userName, User userContainer) throws Exception
    {
        User user = em.find(User.class, userName);
        if (user == null)
            SystemReporter.report("Didn't find user with user name: " + userName, true);

        switch(criteria)
        {
            case UserName:
                //validating that the new user name is unique
                 if (em.find(User.class, userName) != null)
                 {
                    SystemReporter.report("The provided user name: " + userName + " is already exists in the system", true);
                 }

                user.setUserName(userContainer.getUserName());
                SystemReporter.report("Updated user name to: " + userContainer.getUserName());
                break;
            case Id:
                //validating that the new id is unique
                if (em.createQuery("select u from User u where u.id = " + userContainer.getId() ).getFirstResult() == 0)
                {
                    SystemReporter.report("The provided user id: " + userContainer.getId() + " is already exists in the system", true);
                }
                user.setId(userContainer.getId());
                SystemReporter.report(
                        "Updated user id to: " + userContainer.getId());
                break;
            case FirstName:
                user.setFirstName(userContainer.getFirstName());
                SystemReporter.report(
                        "Updated users first name to: " + userContainer.getFirstName());
                break;
            case LastName:
                user.setLastName(userContainer.getLastName());
                SystemReporter.report(
                        "Updated users last name to: " + userContainer.getLastName());
                break;
            case Email:
               //validating that the new id is unique
                  if (em.createQuery("select u from User u where u.email = '" + userContainer.getEmail() +"'").getFirstResult() == 0)
                {
                    SystemReporter.report("The provided user email: " + userContainer.getEmail() + " is already exists in the system", true);
                }

                user.setEmail(userContainer.getEmail());
                SystemReporter.report(
                        "Updated users email to: " + userContainer.getEmail());
                break;
            case Age:
                user.setAge(userContainer.getAge());
                SystemReporter.report(
                        "Updated users age to: " + userContainer.getAge());
                break;

            default:
                SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
        }
        try
        {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            SystemReporter.report(
                    "Catched exception when performed write to DB: " + e.getMessage(), true);
        }
    }

    /**
     * Return the product list that reply to the given criteria
     * the product container will contain the search value
     * @param criteria
     * @param productContainer
     * @return
     * @throws Exception
     */
    public ArrayList<User> getUserByGivenCriteria(UserCriteria criteria, User userContainer) throws Exception
    {
        ArrayList<User> returnList = new ArrayList<User>();
        ArrayList<User> queryList = new ArrayList<User>();
        switch(criteria)
        {
            case FirstName:
                returnList.addAll((ArrayList<User>)em.createQuery("select u from User u whrer u.firstName = " + userContainer.getFirstName())
                        .getResultList());
                break;
            case LastName:
                  returnList.addAll((ArrayList<User>)em.createQuery("select u from User u whrer u.lastName = " + userContainer.getLastName())
                        .getResultList());
                break;
            case UserName:
                returnList.add(em.find(User.class, userContainer.getUserName()));
                break;
            case Id:
                returnList.add((User)em.createQuery("select u from User u whrer u.id = " + userContainer.getUserName()).getSingleResult());
                break;
            case Email:
                returnList.add((User)em.createQuery("select u from User u whrer u.email = " + userContainer.getEmail()).getSingleResult());
                break;
            default:
                SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
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
    public void printUserInfo(String userName) throws Exception
    {
        User userContainer = em.find(User.class, userName);
        printUserInfoImpl(userContainer);
    }

    /**
     * Delete user by given name
     * @param name
     * @throws Exception
     */
    public void deleteUser(String userName) throws Exception
    {
        User user = em.find(User.class, userName);
        if(user.isNull())
            SystemReporter.report("Didn't find user with given name", true);
        try
        {
            em.getTransaction().begin();
            em.remove(user);
            em.flush();
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            SystemReporter.report(
                    "Catched exception when performed write to DB: " + e.getMessage(), true);
        }
    }

    /**
     * Prints user info need to provide user class
     * @param product
     * @throws Exception
     */
    private void printUserInfoImpl(User user) throws Exception
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
              SystemReporter.report("--------------------------------");
    }

    public void printAllUsers() throws Exception
    {
        List<User> users = getAllUsers();
        for (User user: users)
        {
            printUserInfoImpl(user);
        }
    }

    public List<User> getAllUsers()
    {
       Query query = em.createQuery("SELECT u FROM User u");
       return (List<User>) query.getResultList();
    }

    public void createAdminUserIfNeeded() throws Exception
    {
        try
        {
            authenticate("admin", "123456");
        }
        catch (Exception e)
        {
            try
            {
                em.getTransaction().begin();
                em.persist(new AdministratorUser("admin", "admin", "admin",
                        "admin", "admin@root.com", "123456", "99"));
                em.flush();
                em.getTransaction().commit();
            }
            catch(Exception ex)
            {
                if (em.getTransaction().isActive())
                {
                    em.getTransaction().rollback();
                }
                SystemReporter.report(
                        "Catched exception when performed write to DB: " + ex.getMessage(),true);
            }
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
