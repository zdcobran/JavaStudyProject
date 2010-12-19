/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.service;

import java.util.ArrayList;
import javastudyproject.model.Category;
import javastudyproject.model.Product;
import javastudyproject.service.ProductsOpsBean.LergerSmaller;
import javastudyproject.service.ProductsOpsBean.ProductCriteria;

/**
 *
 * @author alon
 */
public interface ProductsOps {

    public void updateProductByName(ProductCriteria criteria, String name, Product productContainer) throws Exception;
    public ArrayList<Product> getProductsByGivenCriteria(ProductCriteria criteria, Product productContainer) throws Exception;
    public void printProductInfo(String name) throws Exception;
    public void deleteProduct(String name) throws Exception;
    public void printProductInfoImpl(Product product) throws Exception;
    public void printProductsByPrice(LergerSmaller by, double price) throws Exception;
    public void printProductsByCategory(Category category) throws Exception;
    public void printMostSaleableProduct() throws Exception;
    public void printSortedProductsByPrice() throws Exception;
    public void printAllCategories() throws Exception;
    public void addNewCategory(String name) throws Exception;
    public ArrayList<Product> getAllProducts();
    public void printAllProducts() throws Exception;
    public ArrayList<Category> getAllCategories();
}
