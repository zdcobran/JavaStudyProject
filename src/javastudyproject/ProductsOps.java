/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject;

import java.util.ArrayList;
import javastudyproject.reporting.SystemReporter;

/**
 * The class handling all products operations
 * @author alon
 */
public class ProductsOps extends ObjectSystem{

    /**
     * Adding new product
     * Verifying that the product is unique
     * @param name
     * @param serialNum
     * @param price
     * @param quantity
     * @param type
     * @throws Exception
     */
    public static void addNewProduct(String name, String serialNum, double price, int quantity, Category category) throws Exception
    {
        for (Product prod : products)
        {
            if (prod.getName().equals(name))
            {
                  SystemReporter.report("product with the same name is exist");
                  return;
            }
            if (prod.getSerialNumber().equals(serialNum))
            {
                  SystemReporter.report("product with the same serial number is exist");
                  return;
            }
        }
        products.add(new Product(name, category, serialNum, price, quantity));
        SystemReporter.report("Product is added");
    }

     public static void addNewCategory(String name) throws Exception
    {
        for (Category cat : categories)
        {
            if (cat.getName().equals(name))
            {
                SystemReporter.report("Category is already exists", true);
                return;
            }
        }
        categories.add(new Category(name));
        SystemReporter.report("Category is added");
    }


    /**
     * Updating the product with given name
     * @param criteria
     * @param name
     * @param productContainer
     * @throws Exception
     */
    public static void updateProductByName(ProductCriteria criteria, String name, Product productContainer) throws Exception
    {
        for (Product product: products)
        {
            if (product.getName().equals(name))
            {
                switch(criteria)
                {
                    case Name:
                        //validating that the new name is unique
                        for (Product productUnique: products)
                        {
                             if (productUnique.getName().equals(productContainer.getName()))
                             {
                                SystemReporter.report(
                                        "The provided name: "
                                        + productContainer.getName() + " is already exists in the system", true);
                             }
                        }

                        product.setName(productContainer.getName());
                        SystemReporter.report("Updated product name to: " + productContainer.getName());
                        return;
                    case SerialName:
                        //validating that the new sn is unique
                        for (Product productUnique: products)
                        {
                             if (productUnique.getSerialNumber().equals(productContainer.getSerialNumber()))
                             {
                                SystemReporter.report(
                                        "The provided SN: "
                                        + productContainer.getSerialNumber() + " is already exists in the system", true);
                             }
                        }

                        product.setSerialNumber(productContainer.getSerialNumber());
                        SystemReporter.report(
                                "Updated product serial number to: " + productContainer.getSerialNumber());
                        return;
                    case Price:
                        product.setPrice(productContainer.getPrice());
                        SystemReporter.report(
                                "Updated product price to: " + productContainer.getPrice());
                        return;
                    case Quantity:
                        product.setQuantity(productContainer.getQuantity());
                        SystemReporter.report(
                                "Updated product quantity to: " + productContainer.getQuantity());
                        return;
                    default:
                        SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
                }
            }
        }
        SystemReporter.report("Didn't find product with name: " + name, true);
    }

    /**
     * Return the product list that reply to the given criteria
     * the product container will contain the search value
     * @param criteria
     * @param productContainer
     * @return
     * @throws Exception
     */
    public static ArrayList<Product> getProductsByGivenCriteria(ProductCriteria criteria, Product productContainer) throws Exception
    {
        ArrayList<Product> returnList = new ArrayList<Product>();
        switch(criteria)
        {
            case Name:
                for (Product product : products)
                {
                    if (product.getName().equals(productContainer.getName()))
                    {
                        returnList.add(product);
                    }
                }
                return returnList;
            case SerialName:
               for (Product product : products)
                {
                    if (product.getSerialNumber().equals(productContainer.getSerialNumber()))
                    {
                        returnList.add(product);
                    }
                }
                return returnList;
            case Price:
               for (Product product : products)
                {
                    if (product.getPrice() == productContainer.getPrice())
                    {
                        returnList.add(product);
                    }
                }
                return returnList;
            case Quantity:
                for (Product product : products)
                {
                    if (product.getQuantity() == productContainer.getQuantity())
                    {
                        returnList.add(product);
                    }
                }
                return returnList;
            case Category:
                for (Product product : products)
                {
                    if (product.getCategory().getName().equals(productContainer.getCategory().getName()))
                    {
                        returnList.add(product);
                    }
                }
                return returnList;
            default:
                SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
        }
        if (returnList.isEmpty())
            SystemReporter.report("Didn't find products for given search", true);
        return returnList;
    }

    /**
     * Prints product info by given name
     * @param name
     * @throws Exception
     */
    public static void printProductInfo(String name) throws Exception
    {
        for (Product product: products)
        {
            if (product.getName().equals(name))
            {
                printProductInfoImpl(product);
                return;
            }
        }
        SystemReporter.report("Didn't find product with given name", true);
    }

    /**
     * Delete product by given name
     * @param name
     * @throws Exception
     */
    public static void deleteProduct(String name) throws Exception
    {
        for (Product product: products)
        {
            if (product.getName().equals(name))
            {
                SystemReporter.report("Deleting product with this info: ");
                printProductInfoImpl(product);
                products.remove(product);
                return;
            }
        }
        SystemReporter.report("Didn't find product with given name", true);
    }

    /**
     * Prints product info need to provide product class
     * @param product
     * @throws Exception
     */
    public static  void printProductInfoImpl(Product product) throws Exception
    {
              SystemReporter.report("Product info:", new String[] {
                    "Product name: " + product.getName(),
                    "Serial number: " + product.getSerialNumber(),
                    "Price: " + product.getPrice(),
                    "Quantity: " + product.getQuantity(),
                    "Category: " + product.getCategory().getName()});
    }

    public static void printAllProducts() throws Exception
    {
        for (Product product:products)
        {
            printProductInfoImpl(product);
        }
    }

    public static void printProductsByPrice(LergerSmaller by, double price) throws Exception
    {
        for (Product product: products)
        {
            switch(by)
            {
                case Larger:
                    if (product.getPrice() >= price)
                    {
                        printProductInfoImpl(product);
                    }
                    break;
                case Smaller:
                    if (product.getPrice() < price)
                    {
                        printProductInfoImpl(product);
                    }
                    break;
            }
        }
    }

    public enum LergerSmaller
    {
        Larger , Smaller
    }

    public enum ProductCriteria
    {
        Name, SerialName, Price, Quantity, Category
    }

}
