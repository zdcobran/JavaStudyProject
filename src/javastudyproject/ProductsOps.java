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
    public static void addNewProduct(String name, String serialNum, double price, int quantity, Category.CategoryType type) throws Exception
    {
        //validating that the new name is unique
        for (Product productUnique: products)
        {
             if (productUnique.getName().equals(name))
             {
                SystemReporter.report("The provided name: " + name + " is already exists in the system", true);
             }
        }
        //validating that the new sn is unique
        for (Product productUnique: products)
        {
             if (productUnique.getSerialNumber().equals(serialNum))
             {
                SystemReporter.report("The provided SN: " + serialNum + " is already exists in the system", true);
             }
        }

        Product newProduct = new Product(name, serialNum, price, quantity, type);
        products.add(newProduct);
        printProductInfoImpl(newProduct);
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
                    case Type:
                        product.setType(productContainer.getType());
                        SystemReporter.report(
                                "Updated product type to: " + productContainer.getType());
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
        for (Product product: products)
        {
            switch(criteria)
            {
                case Name:
                    if (product.getName().equals(productContainer.getName()))
                    {
                        returnList.add(product);
                        return returnList;
                    }
                    break;
                case SerialName:
                   if (product.getSerialNumber().equals(productContainer.getSerialNumber()))
                    {
                        returnList.add(product);
                        return returnList;
                    }
                    break;
                case Price:
                   if (product.getPrice() == (productContainer.getPrice()))
                    {
                        returnList.add(product);
                    }
                    break;
                case Quantity:
                    if (product.getQuantity() == (productContainer.getQuantity()))
                    {
                        returnList.add(product);
                    }
                    break;
                case Type:
                    if (product.getType().equals(productContainer.getType()))
                    {
                        returnList.add(product);
                    }
                    break;
                default:
                    SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
            }
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
    private static  void printProductInfoImpl(Product product) throws Exception
    {
              SystemReporter.report("Product info:", new String[] {
                    "Product name: " + product.getName(),
                    "Serial number: " + product.getSerialNumber(),
                    "Price: " + product.getPrice(),
                    "Quantity: " + product.getQuantity(),
                    "Type: " + product.getType()});
    }

    public enum ProductCriteria
    {
        Name, SerialName, Price, Quantity, Type
    }

}
