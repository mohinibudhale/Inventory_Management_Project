
package edu.iit.sat.itmd4515.mbudhale.service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


public abstract class AbstractService<T> {
    
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    protected Class<T> entityClass;

    protected AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    
    public void create(T entity)
    {
        em.persist(entity);
    }
    public T read(Long id)
    {
        return em.find(entityClass, id);
    }
    public void update(T entity)
    {
        em.merge(entity);
    }
    public void delete(T entity)
    {
        em.remove(em.merge(entity));
    }
    
    protected List<T> findAll(String namedQueryName)
    {
        return em.createNamedQuery(namedQueryName, entityClass).getResultList();
    }
    
}
