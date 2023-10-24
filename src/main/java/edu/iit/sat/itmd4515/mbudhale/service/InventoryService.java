
package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.Inventory;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class InventoryService extends AbstractService<Inventory> {
    
    public InventoryService() {
        super(Inventory.class);
    }
    public List<Inventory> findAll() {
        return super.findAll("Inventory.findAll");
    }
}
