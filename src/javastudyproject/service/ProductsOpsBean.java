package javastudyproject.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javastudyproject.model.Category;
import javastudyproject.model.Order;
import javastudyproject.model.Product;
import javastudyproject.reporting.SystemReporter;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import projectUtils.ProductPriceComparator;

/**
 * The class handling all products operations
 * @author alon
 */
public class ProductsOpsBean implements ProductsOps{


    private EntityManager em;

    public ProductsOpsBean(EntityManager entityManager) {
        em = entityManager;
    }

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
    public void addNewProduct(String name, String serialNum, double price, int quantity, Category category) throws Exception
    {
        try
        {
            em.getTransaction().begin();
            em.persist(new Product(name, category, serialNum, price, quantity));
            em.flush();
            em.getTransaction().commit();
        }
        catch(EntityExistsException e)
        {
            SystemReporter.report("The specific product is already exists. EM exception: " + e.getMessage(), true);
        }
        //ToDoCatch exc
        SystemReporter.report("Product is added");
    }

    public void addNewCategory(String name) throws Exception
    {
        try
        {
            em.getTransaction().begin();
            em.persist(new Category(name));
            em.flush();
            em.getTransaction().commit();
        }
        catch(EntityExistsException e)
        {
            SystemReporter.report("The specific category is already exists. EM exception: " + e.getMessage(), true);
        }
        //ToDoCatch exc
        SystemReporter.report("Category is added");
    }

    public void printAllCategories() throws Exception
    {
         SystemReporter.report("Printing all categories: ");
         List<Category> categories = getAllCategories();
         for (Category category: categories)
         {
             SystemReporter.report(
                     "Category run id : " + category.getRunId() + " name: " + category.getName());
         }
     }

    /**
     * Updating the product with given name
     * @param criteria
     * @param name
     * @param productContainer
     * @throws Exception
     */
    public void updateProductByName(ProductCriteria criteria, String name, Product productContainer) throws Exception
    {
        em.getTransaction().begin();
        Product product = em.find(Product.class, name);
        switch(criteria)
        {
            case name:
                //TODO: validating that the new name is unique
                product.setName(productContainer.getName());
                SystemReporter.report("Updating product name to: " + productContainer.getName());
                break;
            case serialNum:
                //TODO: validating that the new sn is unique
                product.setSerialNumber(productContainer.getSerialNumber());
                SystemReporter.report(
                        "Updating product serial number to: " + productContainer.getSerialNumber());
                break;
            case price:
                product.setPrice(productContainer.getPrice());
                SystemReporter.report(
                        "Updating product price to: " + productContainer.getPrice());
                break;
            case quantity:
                product.setQuantity(productContainer.getQuantity());
                SystemReporter.report(
                        "Updating product quantity to: " + productContainer.getQuantity());
                break;
            case category:
                product.setCategory(productContainer.getCategory());
                SystemReporter.report(
                        "Updating product category to: " + productContainer.getCategory().getName());
                break;
            default:
                SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
        }
        em.merge(product);
        em.flush();
        em.getTransaction().commit();
    }

    /**
     * Return the product list that reply to the given criteria
     * the product container will contain the search value
     * @param criteria
     * @param productContainer
     * @return
     * @throws Exception
     */
    public List<Product> getProductsByGivenCriteria(ProductCriteria criteria, Product productContainer) throws Exception
    {
        List<Product> returnList = new ArrayList<Product>();
        Query query;
        switch(criteria)
        {
            case name:
                returnList.add(em.find(Product.class, productContainer.getName()));
                return returnList;
            case serialNum:
               query = em.createQuery("SELECT c FROM Product c where c.serialNum = " + productContainer.getSerialNumber());
               returnList.addAll((List<Product>) query.getResultList());
               break;
            case price:
                query = em.createQuery("SELECT c FROM Product c where c.price = " + productContainer.getPrice());
                returnList.addAll((List<Product>) query.getResultList());
                break;
            case quantity:
                query = em.createQuery("SELECT c FROM Product c where c.quantity = " + productContainer.getQuantity());
                returnList.addAll((List<Product>) query.getResultList());
                break;
            case category:
                query = em.createQuery("SELECT c FROM Product c where c.category = " + productContainer.getCategory().getName()); //TODO: i fill this will not work
                returnList.addAll((List<Product>) query.getResultList());
                break;
            default:
                SystemReporter.report("Wrong criteria provided: " + criteria + ", not as expected", true);
        }
        if (returnList.isEmpty())
            SystemReporter.report("Didn't find products for given search", true);
        return returnList;
    }

    /*
     * Prints product info by given name
     * @param name
     * @throws Exception
     */
    public void printProductInfo(String name) throws Exception
    {
        Product product = em.find(Product.class, name);
        printProductInfoImpl(product);
        if (product == null)
            SystemReporter.report("Didn't find product with given name", true);
    }

    /**
     * Delete product by given name
     * @param name
     * @throws Exception
     */
    public void deleteProduct(String name) throws Exception
    {
        em.getTransaction().begin();
        Product product = em.find(Product.class, name);
        if (product == null)
            SystemReporter.report("Cannot find product" + name);
        em.remove(product);
        em.getTransaction().begin();

        //TODO: Catch exc ; SystemReporter.report("Didn't find product with given name", true);
    }

    /**
     * Prints product info need to provide product class
     * @param product
     * @throws Exception
     */
    public void printProductInfoImpl(Product product) throws Exception
    {
              SystemReporter.report("Product info:", new String[] {
                    "Product name: " + product.getName(),
                    "Serial number: " + product.getSerialNumber(),
                    "Price: " + product.getPrice(),
                    "Quantity: " + product.getQuantity(),
                    "Category: " + product.getCategory().getName()});
              SystemReporter.report("-----------------------------------");

    }

    public void printAllProducts() throws Exception
    {
        List<Product> products = getAllProducts();
        if (products.isEmpty())
        {
            SystemReporter.report("There is no products in the system", true);
        }
        for (Product product:products)
        {
            printProductInfoImpl(product);
        }
    }

    /**
     * Printing products by it price when the by value is larger or smaller than
     * the provided amount
     * @param by
     * @param price
     * @throws Exception
     */
    public void printProductsByPrice(LergerSmaller by, double price) throws Exception
    {
        Query query;
        List<Product> products = new ArrayList<Product>();
        SystemReporter.report(
                "Printing products with price " + by.toString() + " than " + price);
        switch(by)
        {
            case Larger:
                query = em.createQuery("SELECT p FROM product p where p.price >= " + price);
                products = (List<Product>) query.getResultList();
                break;
            case Smaller:
                query = em.createQuery("SELECT p FROM product p where p.price < " + price);
                products = (List<Product>) query.getResultList();
                break;
        }
        if (products.isEmpty())
        {
            SystemReporter.report("There is no products in the system", true);
        }
        for (Product product: products)
        {
            printProductInfoImpl(product);
        }

    }

    /**
     * Printing all products by given category
     * @param category
     * @throws Exception
     */
    public void printProductsByCategory(Category category) throws Exception
    {
        Query query;
        List<Product> products = new ArrayList<Product>();
        query = em.createQuery("SELECT p FROM product p where p.category = " + category);
        products =  query.getResultList();
        if (products.isEmpty())
        {
            SystemReporter.report("There is no products in the system", true);
        }

        SystemReporter.report("Printing all product for category:" + category.getName());
        for (Product product: products)
        {
            printProductInfoImpl(product);
        }
    }

    /**
     * print the most saleable product
     * @throws Exception
     */
    public void printMostSaleableProduct() throws Exception
    {
        //TODO:Add the correct query
        //        if (orders.isEmpty())
        //        {
        //            SystemReporter.report("There is no orders in the system", true);
        //        }
        //        HashMap<Product, Integer> productAmount =  new HashMap<Product, Integer>();
        //        for (Order order : orders)
        //        {
        //            for (Product product : order.getProducts())
        //            {
        //                if (productAmount.containsKey(product))
        //                {
        //                    int currAmount = productAmount.get(product);
        //                    currAmount += currAmount + product.getQuantity();
        //                    productAmount.put(product, currAmount);
        //                }
        //                else
        //                {
        //                    productAmount.put(product, product.getQuantity());
        //                }
        //            }
        //        }
        //        Product mostSaleble = new Product();
        //        int max = 0;
        //        for (Product product : productAmount.keySet())
        //        {
        //            if (productAmount.get(product) >= max)
        //            {
        //                max = productAmount.get(product);
        //                mostSaleble = product;
        //            }
        //        }
        //        SystemReporter.report("The most saleable product is: " + mostSaleble.getName() + " with sales of: " + max);
    }

    /**
     * print all products by sorted price
     * @throws Exception
     */
    public void printSortedProductsByPrice() throws Exception
    {
            //TODO:Add the correct query
            //        ArrayList<Product> productsCopy = (ArrayList<Product>) products.clone();
            //        Collections.sort(productsCopy, new ProductPriceComparator());
            //        SystemReporter.report("Printing sorted products by price: ");
            //        for(Product product : productsCopy)
            //        {
            //            printProductInfoImpl(product);
            //        }
    }

    public enum LergerSmaller
    {
        Larger , Smaller
    }

    public enum ProductCriteria
    {
        name, serialNum, price, quantity, category
    }

    public List<Product> getAllProducts()
    {
       Query query = em.createQuery("SELECT o FROM Product o");
       return  query.getResultList();
    }

    public List<Category> getAllCategories()
    {
       Query query = em.createQuery("SELECT c FROM Category c");
       return  query.getResultList();
    }
}
