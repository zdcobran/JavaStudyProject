/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.menus.textual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javastudyproject.Product;
import javastudyproject.Category;
import javastudyproject.ObjectSystem;
import javastudyproject.ProductsOps;
import javastudyproject.reporting.SystemReporter;

/**
 *
 * @author eyarkoni
 */
public class ProductManagementScreen extends ObjectSystem {

    private BufferedReader reader;

    public ProductManagementScreen() {

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Products Management\n-----------------------------------------------");
        System.out.println("1. Add new Product");
        System.out.println("2. Add new Category");
        System.out.println("3. Search product");
        System.out.println("4. Update existing product");
        System.out.println("5. Print product info\n");
        System.out.println("6. Delete product\n");
        System.out.println("7. Back to main menu\n");
        System.out.print("Your choise: ");

        try {
           int choise=  Integer.parseInt(reader.readLine());
           switch (choise)
           {
               case 1:
               {
                    AddNewProduct();
               }break;
               case 2:
               {
                    AddNewCategory();
               }break;
               case 3:
               {
                    SearchProduct();
               }break;
                case 4:
               {
                    UpdateProduct();
               }break;
                case 5:
               {
                    System.out.print("enter product name: ");
                    String prodname =  reader.readLine();
                    ProductsOps.printProductInfo(prodname);
               }break;
                case 6:
               {
                    System.out.print("enter product name: ");
                    String prodName =  reader.readLine();
                    ProductsOps.deleteProduct(prodName);
               }break;
               case 7:
               {
                    new AdministratorScreen();
               }break;
           }
           new ProductManagementScreen();

        } catch (IOException ex) {}
            catch (Exception ex) {}
    }

    private void AddNewProduct() throws IOException {

        try {
            System.out.print("name: ");
            String name = reader.readLine();
            System.out.print("serial number: ");
            String serial = reader.readLine();
            System.out.print("price: ");
            double price = Double.parseDouble(reader.readLine());
            System.out.print("quantity: ");
            int quantity = Integer.parseInt(reader.readLine());
            System.out.println("Select category from list -> ");
            if ( categories.isEmpty() )
            {
                SystemReporter.report("There is no categories yet, please create one", true);
            }
            for (int i=0; i< categories.size(); i++)
                System.out.println(i+". "+categories.get(i).getName());
            System.out.print("Select category Number:  ");
            int catnum = Integer.parseInt(reader.readLine());
            Category category = categories.get(catnum);
            // --- Creating the product ---
            ProductsOps.addNewProduct(name, serial, price, quantity,category);
        }
        catch (IOException ee) {}
        catch (Exception e) 
        {
            System.out.print("There was problem in your last request.\n Details: " + e.getMessage());
            System.out.print("Going back to the products menu...");
            new ProductManagementScreen();
        }
    }

    private void AddNewCategory() {
        try {
            System.out.print("Category name: ");
            String name = reader.readLine();
            ProductsOps.addNewCategory(name);
        }
        catch (IOException ex) {}
        catch (Exception e)
        {
            System.out.print("There was problem in your last request.\n Details: " + e.getMessage());
            System.out.print("Going back to the products menu...");
            new ProductManagementScreen();
        }
    }

    private void SearchProduct() {

        try {
            System.out.print("search (1. by serial number  2. by name 3. by price 4. by category): ");
            int searchby =  Integer.parseInt(reader.readLine());
            switch (searchby)
            {
                case 1: {
                    System.out.print("serial number: ");
                     String serialNum =  reader.readLine();
                     Product prodContainer = new Product();
                     prodContainer.setSerialNumber(serialNum);
                     ArrayList<Product> plist = ProductsOps.getProductsByGivenCriteria(ProductsOps.ProductCriteria.SerialName, prodContainer);
                     for (Product prodInList : plist)
                            ProductsOps.printProductInfoImpl(prodInList);
                }break;
                case 2: {
                    System.out.print("name: ");
                    String name = reader.readLine();
                    Product prodContainer = new Product();
                     prodContainer.setName(name);
                    ArrayList<Product> plist = ProductsOps.getProductsByGivenCriteria(ProductsOps.ProductCriteria.Name, prodContainer);
                     for (Product prodInList : plist)
                            ProductsOps.printProductInfoImpl(prodInList);
                }break;
                case 3: {
                    System.out.print("price: ");
                    double price = Double.parseDouble(reader.readLine());
                    Product prodContainer = new Product();
                     prodContainer.setPrice(price);
                      ArrayList<Product> plist = ProductsOps.getProductsByGivenCriteria(ProductsOps.ProductCriteria.Price, prodContainer);
                     for (Product prodInList : plist)
                            ProductsOps.printProductInfoImpl(prodInList);
                }break;
                case 4: {
                    System.out.print("category: ");
                     String category = reader.readLine();                     Product prodContainer = new Product();
                     Category categoryContainer=  new Category(category);
                     prodContainer.setCategory(categoryContainer);
                      ArrayList<Product> plist = ProductsOps.getProductsByGivenCriteria(ProductsOps.ProductCriteria.Category, prodContainer);
                     for (Product prodInList : plist)
                            ProductsOps.printProductInfoImpl(prodInList);
                }break;
            }
        }
        catch (IOException e) {}
        catch (Exception e)
        {
            System.out.print("There was problem in your last request.\n Details: " + e.getMessage());
            System.out.print("Going back to the products menu...");
            new ProductManagementScreen();
        }
    }

    private void UpdateProduct() {

        try {
            System.out.print("Select product by name: ");
            String prodName = reader.readLine();
            System.out.print("Select product property to change (1. category 2. serialNumber 3. price 4. quantity):");
           int fieldChangeInder = Integer.parseInt(reader.readLine());
           switch (fieldChangeInder)
           {
               case 1:
               {
                      System.out.println("Select new category: ");
                      for (int i=0;i<categories.size();i++)
                          System.out.println(i+". "+categories.get(i).getName());
                      int selectedCategoryIndex = Integer.parseInt(reader.readLine());
                      Product prodContainder = new Product();
                      prodContainder.setCategory(categories.get(selectedCategoryIndex));
                      ProductsOps.updateProductByName(ProductsOps.ProductCriteria.Category, prodName, prodContainder);
               }
                break;
               case 2:
               {
                      System.out.println("Type new serial number: ");
                      String snumber = reader.readLine();
                      Product prodContainer = new Product();
                      prodContainer.setSerialNumber(snumber);
                      ProductsOps.updateProductByName(ProductsOps.ProductCriteria.SerialName, prodName, prodContainer);
               }
                break;
               case 3:
               {
                      System.out.println("Type new price: ");
                      double newprice = Double.parseDouble(reader.readLine());
                      Product prodContainer = new Product();
                      prodContainer.setPrice(newprice);
                      ProductsOps.updateProductByName(ProductsOps.ProductCriteria.Price, prodName, prodContainer);
               }
               break;
               case 4:
               {
                       System.out.println("Type new quantity: ");
                      int newquantity = Integer.parseInt(reader.readLine());
                      Product prodContainer = new Product();
                      prodContainer.setQuantity(newquantity);
                      ProductsOps.updateProductByName(ProductsOps.ProductCriteria.Quantity, prodName, prodContainer);
               }
               break;
           }

        } catch(IOException ee) {}
        catch (Exception e)
        {
            System.out.print("There was problem in your last request.\n Details: " + e.getMessage());
            System.out.print("Going back to the products menu...");
            new ProductManagementScreen();
        }
    }



}
