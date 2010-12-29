package javastudyproject.service;

import java.util.List;
import javastudyproject.model.Category;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * The class handling all products operations
 * @author alon
 */
public class CategoryOpsBean implements CategoryOps{


    private EntityManager em;

    public CategoryOpsBean(EntityManager entityManager) {
        em = entityManager;
    }

    
    public List<Category> getAllCategories()
    {
       Query query = em.createQuery("SELECT o FROM Category o");
       return (List<Category>) query.getResultList();
    }
}
