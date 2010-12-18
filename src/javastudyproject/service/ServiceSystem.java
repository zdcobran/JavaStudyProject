/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javastudyproject.service;

import java.util.ArrayList;
import javastudyproject.db.FilesDB;
import javastudyproject.service.OrderOpsBean;
import javastudyproject.service.ProductsOpsBean;
import javastudyproject.service.UserOpsBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author EladYarkoni
 */
public class ServiceSystem {

    protected EntityManager em;
    protected OrderOpsBean orderService;
    protected ProductsOpsBean productService;
    protected UserOpsBean userService;
    protected CategoryOpsBean categoryService;

    public ServiceSystem ()
    {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaStudyProjectService");
       em = emf.createEntityManager();

       orderService = new OrderOpsBean(em);
       productService = new ProductsOpsBean(em);
       userService = new UserOpsBean(em);
       categoryService = new CategoryOpsBean(em);
    }


}
