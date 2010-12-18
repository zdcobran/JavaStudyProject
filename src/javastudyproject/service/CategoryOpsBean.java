package javastudyproject.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javastudyproject.model.Category;
import javastudyproject.model.Order;
import javastudyproject.model.Product;
import javastudyproject.reporting.SystemReporter;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import projectUtils.ProductPriceComparator;

/**
 * The class handling all products operations
 * @author alon
 */
public class CategoryOpsBean{


    private EntityManager em;

    public CategoryOpsBean(EntityManager entityManager) {
        em = entityManager;
    }

    
    public ArrayList<Category> getAllCategories()
    {
       Query query = em.createQuery("SELECT o FROM Category o");
       return (ArrayList<Category>) query.getResultList();
    }
}
