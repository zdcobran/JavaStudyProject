/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author EladYarkoni
 */
public class ServiceSystem {

    protected EntityManagerFactory emf;
    protected EntityManager em;
    protected OrderOps orderService;
    protected ProductsOps productService;
    protected UserOps userService;
    protected CategoryOps categoryService;

    public ServiceSystem ()
    {
       emf = Persistence.createEntityManagerFactory("JavaStudyProject");
       em = emf.createEntityManager();

       orderService = new OrderOpsBean(em);
       productService = new ProductsOpsBean(em);
       userService = new UserOpsBean(em);
       categoryService = new CategoryOpsBean(em);

       userService.createAdminUserIfNeeded(); //Creating the first user admin user if needed
    }

    public void cleanup()
    {
        em.close();
        emf.close();
    }


}
