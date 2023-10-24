
package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductService {
    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;
    public ProductService() {
    }
    public void create(Product p)
    {
        em.persist(p);
    }
    public Product read(Long id)
    {
        return em.find(Product.class, id);
    }
    public void update(Product p)
    {
        em.merge(p);
    }
    public void delete(Product p)
    {
        em.remove(em.merge(p));
    }
    
    public List<Product> findAll()
    {
        return em.createNamedQuery("product.findAll", Product.class).getResultList();
    }
}
