
package edu.iit.sat.itmd4515.mbudhale.service;
import edu.iit.sat.itmd4515.mbudhale.domain.AllOrder;
import jakarta.ejb.Stateless;
import java.util.List;
@Stateless
public class OrderService extends AbstractService<AllOrder>{

    public OrderService() {
        super(AllOrder.class);
    }
    
    
    public List<AllOrder> findAll()
    {
        return super.findAll("AllOrder.findAll");
    }
    
}
