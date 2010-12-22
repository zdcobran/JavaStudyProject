/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javastudyproject.model.Product;
import javastudyproject.model.Category;
import javastudyproject.service.ServiceSystem;
import javastudyproject.service.ProductsOpsBean;
import javastudyproject.reporting.SystemReporter;

/**
 * the product management screen for administrator user
 * @author eyarkoni
 */
public class ProductManagementScreen extends ServiceSystem {

    private BufferedReader reader;


    public ProductManagementScreen() throws Exception {

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
                    productService.printProductInfo(prodname);
               }break;
                case 6:
               {
                    ArrayList<Product> products = productService.getAllProducts();
                    for (int i=0; i<products.size(); i++)
                       System.out.println(i+ ". " + products.get(i).getName());
                    System.out.print("Select product: ");
                    int prodIndex = Integer.parseInt(reader.readLine());
                    productService.deleteProduct(products.get(prodIndex).getName());
               }break;
               case 7:
               {
                    new AdministratorScreen();
               }break;
           }
           new ProductManagementScreen();

        } 
        catch (Exception e)
        {
            SystemReporter.report("Failed to perform last action, error: " + e.getMessage());
            new ProductManagementScreen();
        }
    }

    /**
     * adding a new product
     * @throws IOException
     */
    private void AddNewProduct() throws IOException, Exception {

        ArrayList<Category> categories = categoryService.getAllCategories();

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
            SystemReporter.report("There is no categories yet, please create one\n", true);
        }
        for (int i=0; i< categories.size(); i++)
            System.out.println(i+". "+categories.get(i).getName());
        System.out.print("Select category Number:  ");
        int catnum = Integer.parseInt(reader.readLine());
        Category category = categories.get(catnum);
        // --- Creating the product ---
        productService.addNewProduct(name, serial, price, quantity,category);
    }
    /**
     * adding a new category
     */
    private void AddNewCategory() throws Exception {
        System.out.print("Category name: ");
        String name = reader.readLine();
        productService.addNewCategory(name);
    }
    /**
     * search a product by 4 fields
     */
    private void SearchProduct() throws Exception
    {
        System.out.print("search (1. by serial number  2. by name 3. by price 4. by category): ");
        int searchby =  Integer.parseInt(reader.readLine());
        switch (searchby)
        {
            case 1: {
                System.out.print("serial number: ");
                 String serialNum =  reader.readLine();
                 Product prodContainer = new Product();
                 prodContainer.setSerialNumber(serialNum);
                 ArrayList<Product> plist = productService.getProductsByGivenCriteria(ProductsOpsBean.ProductCriteria.serialNum, prodContainer);
                 for (Product prodInList : plist)
                        productService.printProductInfoImpl(prodInList);
            }break;
            case 2: {
                System.out.print("name: ");
                String name = reader.readLine();
                Product prodContainer = new Product();
                 prodContainer.setName(name);
                ArrayList<Product> plist = productService.getProductsByGivenCriteria(ProductsOpsBean.ProductCriteria.name, prodContainer);
                 for (Product prodInList : plist)
                        productService.printProductInfoImpl(prodInList);
            }break;
            case 3: {
                System.out.print("price: ");
                double price = Double.parseDouble(reader.readLine());
                Product prodContainer = new Product();
                 prodContainer.setPrice(price);
                  ArrayList<Product> plist = productService.getProductsByGivenCriteria(ProductsOpsBean.ProductCriteria.price, prodContainer);
                 for (Product prodInList : plist)
                        productService.printProductInfoImpl(prodInList);
            }break;
            case 4: {
                System.out.print("category: ");
                 String category = reader.readLine();
                 Product prodContainer = new Product();
                 Category categoryContainer=  new Category(category);
                 prodContainer.setCategory(categoryContainer);
                  ArrayList<Product> plist = productService.getProductsByGivenCriteria(ProductsOpsBean.ProductCriteria.category, prodContainer);
                 for (Product prodInList : plist)
                        productService.printProductInfoImpl(prodInList);
            }break;
        }
    }

    /**
     * update selected product
     */
    private void UpdateProduct() throws Exception
    {
        ArrayList<Product> products = productService.getAllProducts();
        ArrayList<Category> categories = categoryService.getAllCategories();

        for (int i=0;i<products.size();i++)
            System.out.println(i+ ". " + products.get(i).getName());
        System.out.print("Select product: ");
        int prodIndex = Integer.parseInt(reader.readLine());
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
                  productService.updateProductByName(ProductsOpsBean.ProductCriteria.category, products.get(prodIndex).getName(), prodContainder);
           }
            break;
           case 2:
           {
                  System.out.println("Type new serial number: ");
                  String snumber = reader.readLine();
                  Product prodContainer = new Product();
                  prodContainer.setSerialNumber(snumber);
                  productService.updateProductByName(ProductsOpsBean.ProductCriteria.serialNum,
                          products.get(prodIndex).getName(),
                          prodContainer);
           }
            break;
           case 3:
           {
                  System.out.println("Type new price: ");
                  double newprice = Double.parseDouble(reader.readLine());
                  Product prodContainer = new Product();
                  prodContainer.setPrice(newprice);
                  productService.updateProductByName(ProductsOpsBean.ProductCriteria.price,
                          products.get(prodIndex).getName(),
                          prodContainer);
           }
           break;
           case 4:
           {
                   System.out.println("Type new quantity: ");
                  int newquantity = Integer.parseInt(reader.readLine());
                  Product prodContainer = new Product();
                  prodContainer.setQuantity(newquantity);
                  productService.updateProductByName(ProductsOpsBean.ProductCriteria.quantity, products.get(prodIndex).getName(), prodContainer);
           }
           break;
       }
    }
}
