/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javastudyproject.*;
import javastudyproject.db.FilesDB;
import javastudyproject.reporting.SystemReporter;
import javastudyproject.users.User;
import javastudyproject.users.UserOps;
import javastudyproject.users.UserOps.UserType;

/**
 *
 * @author eyarkoni
 */
public class ReadWriteUserScreen extends ObjectSystem {

    private BufferedReader reader;
    private User workingUser;

    public ReadWriteUserScreen(User user) throws Exception {

        reader = new BufferedReader(new InputStreamReader(System.in));
        workingUser = user;
        System.out.println("User  menu\n-----------------------------------------------");
        System.out.println("1. Create new user");
        System.out.println("2. Update your user settings");
        System.out.println("3. Create Order");
        System.out.println("4. Exit\n");
        System.out.print("Your choise: ");

        try {
            int choise=  Integer.parseInt(reader.readLine());
            switch (choise) {
                case 1: {
                    CreateNewUser();
                } break;
                case 2: {
                   UpdateExistUser();
                } break;
                case 3: {
                    CreateNewOrder();
                }
                 case 4: {
                    new LoginScreen();
                }
            }
            new ReadWriteUserScreen(workingUser);
        }
        catch (IOException ex) {}
        catch (Exception e)
        {
            SystemReporter.report("Failed to perform last action, error: " + e.getMessage());
            new ReadWriteUserScreen(workingUser);
        }
    }

    private void  CreateNewUser() throws Exception
    {
        try {
            System.out.print("user type (1. ReadWrite 3. ReadOnly): ");
            int userType = Integer.parseInt(reader.readLine());
            System.out.print("first name: ");
            String origName = reader.readLine();
            System.out.print("last name: ");
            String lastName = reader.readLine();
            System.out.print("user id: ");
            String id = reader.readLine();
            System.out.print("user name: ");
            String userName = reader.readLine();
            System.out.print("user password: ");
            String password = reader.readLine();
            System.out.print("user email: ");
            String email = reader.readLine();
            System.out.print("user age: ");
            String age = reader.readLine();
            switch (userType) {
                case 1: {
                    UserOps.addNewUser(UserType.ReadWriteUser,
                            userName, id, lastName, lastName, email, password, age);
                }break;
                case 2: {
                    for (Order order : orders)
                    {
                        if (order.getUser().getUserName().equals(workingUser.getUserName()))
                            System.out.print("Order id " + order.getRunId());
                    }
                    System.out.print("Enter the order id for the read only user");
                    int orderId = Integer.parseInt(reader.readLine());
                    UserOps.addNewUser(UserType.ReadOnlyUser,
                            userName, id, lastName, lastName,
                            email, password, age, workingUser.getUserName(), orderId);
                }break;
            }
            FilesDB.UpdateUsers(users);
        }
        catch (IOException ee) 
        {

        }
        catch (Exception e)
        {
            SystemReporter.report("Failed to add user, error: " + e.getMessage());
            System.out.println("1. Try again");
            System.out.println("2. Back to main menu\n");

            System.out.println("Ente your choise: ");

            int choise=  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1:
                    this.CreateNewUser();
                    break;
                case 2:
                    new ReadWriteUserScreen(workingUser);
                    break;
            }
        }
    }

    private void UpdateExistUser() throws Exception
    {
        try {
            System.out.print("Select user name to update: ");
            String  username = reader.readLine();
            User selectedUser = UserOps.getUserByGivenCriteria(UserOps.UserCriteria.UserName, new User().setUserName(username)).get(0);
            if (selectedUser.toString().equals("AdministratorUser"))
                SystemReporter.report("Cannot edit user: " + username + " insufficient  permissions");
            SystemReporter.report("Select field to update ",
                new String[] {
                "1. User name",
                "2. Fisrt name",
                "3. Last name",
                "4. Email",
                "5. Password",
                "6. Age"});

            int fieldchoise = Integer.parseInt(reader.readLine());
            System.out.print("Enter new value");
            String newValue = reader.readLine();

            switch (fieldchoise)
            {
                case 1:
                {
                    UserOps.updateUserDetailsByUserName(
                            UserOps.UserCriteria.UserName,
                            username, new User().setUserName(newValue));
                }break;
                case 2:
                {
                    UserOps.updateUserDetailsByUserName(
                            UserOps.UserCriteria.FirstName,
                            username, new User().setFirstName(newValue));
                }break;
                case 3:
                {
                    UserOps.updateUserDetailsByUserName(
                            UserOps.UserCriteria.LastName,
                            username, new User().setLastName(newValue));
                }break;
                case 4:
                {
                    UserOps.updateUserDetailsByUserName(
                            UserOps.UserCriteria.Email,
                            username, new User().setEmail(newValue));
                }break;
                case 5:
                {
                    UserOps.updateUserDetailsByUserName(
                            UserOps.UserCriteria.Password,
                            username, new User().setPassword(newValue));
                }break;
                case 6:
                {
                    UserOps.updateUserDetailsByUserName(
                            UserOps.UserCriteria.Age,
                            username, new User().setAge(newValue));
                }break;
            }
            saveUsers();
        }
        catch (IOException ee) {}
        catch (Exception e)
        {
            SystemReporter.report("Failed to edit user, error: " + e.getMessage());
            System.out.println("1. Try again");
            System.out.println("2. Back to main menu\n");

            System.out.println("Ente your choise: ");

            int choise=  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1:
                    this.UpdateExistUser();
                    break;
                case 2:
                    new ReadWriteUserScreen(workingUser);
                    break;
            }
        }
    }

    public void CreateNewOrder() throws Exception
    {
        ArrayList<Product> myProductList = new ArrayList<Product>();
        try {
            int selectedProductNumber = 1;
            while (selectedProductNumber != 0)
            {
                System.out.println("Products list\n----------------------------");
                for (int i=0; i<products.size();i++)
                {
                    System.out.println(i+1+". "+ products.get(i).getName());
                }

                System.out.print("Add product number (Type '0' close list and continue): ");
                selectedProductNumber = Integer.parseInt(reader.readLine());
                if (selectedProductNumber == 0)
                    continue;
                System.out.print("Type product quantity: ");
                int quantity = Integer.parseInt(reader.readLine());
                Product selectedProduct = products.get(selectedProductNumber - 1);
                if (quantity > selectedProduct.getQuantity())
                {
                    System.out.print("The provided quantity is greater than the actual, actual: " + selectedProduct.getQuantity());
                    continue;
                }
                myProductList.add(selectedProduct.setQuantity(quantity));
            }

            System.out.print("Select Delivery type (1. Self 2. Shipping): ");
            int deliveryTypeNum = Integer.parseInt(reader.readLine());
            Order.DeliveryType deliveryType = Order.DeliveryType.None ;
            switch (deliveryTypeNum)
            {
                case 1: {deliveryType =Order.DeliveryType.Self; }break;
                case 2: {deliveryType =Order.DeliveryType.Shipping; }break;
            }

            System.out.print("Enter Delivery date (Format: dd/MM/yy hh:mm)\n");
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy hh:mm");
            String dateString = reader.readLine();
            Date date = df1.parse(dateString);

            System.out.println("1. Save order");
            System.out.println("2. Back to main menu\n");
            System.out.print("Your choise: ");

            int choise=  Integer.parseInt(reader.readLine());
            switch (choise) {
                case 1:
                    Order newOrder = new Order(workingUser, date, deliveryType);
                    newOrder = updateOrderTotalPrice(newOrder);
                    updateTheProductsQuantityByOrder(newOrder);
                    orders.add(newOrder);
                    saveOrders();
                    break;
                case 2:
                    new ReadWriteUserScreen(workingUser);
                    break;
            }
            new ReadWriteUserScreen(workingUser);
        }
        catch (IOException ex) {}
        catch (Exception e)
        {
            SystemReporter.report("Failed to add order, error: " + e.getMessage());
            System.out.println("1. Try again");
            System.out.println("2. Back to main menu\n");

            System.out.println("Ente your choise: ");

            int choise=  Integer.parseInt(reader.readLine());
            switch (choise)
            {
                case 1:
                    this.CreateNewOrder();
                    break;
                case 2:
                    new ReadWriteUserScreen(workingUser);
                    break;
            }
        }
    }

    private Order updateOrderTotalPrice(Order order)
    {
        for (Product product: order.getProducts())
        {
            order.updateTotalPrice(product.getPrice() * product.getQuantity());
        }
        return order;
    }

    private void updateTheProductsQuantityByOrder(Order order)
    {
        for (Product product: order.getProducts())
        {
            for (Product genProduct: products)
            {
                if(product.getName().equals(genProduct.getName()))
                {
                    genProduct.setQuantity(genProduct.getQuantity() - product.getQuantity());
                    break;
                }
            }
        }
    }
}
