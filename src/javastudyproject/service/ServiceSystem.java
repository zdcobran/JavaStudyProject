/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Initializing all interfaces and the entity manager
 * @author EladYarkoni
 */
public class ServiceSystem {

    protected EntityManagerFactory emf;
    protected EntityManager em;
    protected OrderOpsBean orderService;
    protected ProductsOpsBean productService;
    protected UserOpsBean userService;
    protected CategoryOpsBean categoryService;

    public ServiceSystem ()
    {
       emf = Persistence.createEntityManagerFactory("JavaStudyProject");
       em = emf.createEntityManager();

       orderService = new OrderOpsBean(em);
       productService = new ProductsOpsBean(em);
       userService = new UserOpsBean(em);
       categoryService = new CategoryOpsBean(em);
       try
       {
            userService.createAdminUserIfNeeded(); //Creating the first user admin user if needed
       }
       catch (Exception e)
       {
           System.out.println("Failed to create the first admin user :(");
       }
    }

    public void cleanup()
    {
        em.close();
        emf.close();
    }
}
