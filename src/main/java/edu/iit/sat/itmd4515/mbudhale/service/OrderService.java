package edu.iit.sat.itmd4515.mbudhale.service;
import edu.iit.sat.itmd4515.mbudhale.domain.AllOrder;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

@Stateless
@Named
public class OrderService extends AbstractService<AllOrder> {

    public OrderService() {
        super(AllOrder.class);
    }

    public List<AllOrder> findAll() {
        List<AllOrder> orders = super.findAll("AllOrder.findAll");
        System.out.println("Number of orders retrieved: " + orders.size());
        return super.findAll("AllOrder.findAll");
    }

    public List<AllOrder> findById(Long id) {
        return em.createNamedQuery("AllOrder.findById", AllOrder.class)
                .setParameter("id", id)
                .getResultList();
    }
    public AllOrder findOrderById(Long id) {
        return em.createNamedQuery("AllOrder.findOrderById", AllOrder.class)
                .setParameter("id", id)
                .getSingleResult();
    }    

    public void cancelOrder(AllOrder order) {
        AllOrder managedOrder = em.find(AllOrder.class, order.getId());
        if (managedOrder != null) {
            delete(managedOrder);
            System.out.println("Order canceled: " + order.getId());
        } else {
            System.out.println("Order with ID " + order.getId() + " not found for cancellation.");
        }
    }    
     
}
