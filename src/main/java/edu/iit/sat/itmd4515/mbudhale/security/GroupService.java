
package edu.iit.sat.itmd4515.mbudhale.security;

import edu.iit.sat.itmd4515.mbudhale.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class GroupService extends AbstractService<Group>{

    public GroupService() {
        super(Group.class);
    }
    
     public List<Group> findAll()
    {
        return super.findAll("Group.findAll");
    }
    
}
