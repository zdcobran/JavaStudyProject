/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.service;

import java.util.ArrayList;
import javastudyproject.model.User;
import javastudyproject.service.UserOpsBean.UserCriteria;
import javastudyproject.service.UserOpsBean.UserType;

/**
 *
 * @author alon
 */
public interface UserOps {

    public void UpdateUsers();
    public User authenticate(String userName, String password) throws Exception;
    public void addNewUser(
            UserType type,
            String userName,
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String age) throws Exception;
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
            int readOnlyUserOrderId) throws Exception;
     public void updateUserDetailsByUserName(UserCriteria criteria, String userName, User userContainer) throws Exception;
     public ArrayList<User> getUserByGivenCriteria(UserCriteria criteria, User userContainer) throws Exception;
     public void printUserInfo(String userName) throws Exception;
     public void deleteUser(String userName) throws Exception;
     public ArrayList<User> getAllUsers();

}