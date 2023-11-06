
package edu.iit.sat.itmd4515.mbudhale.security;

import edu.iit.sat.itmd4515.mbudhale.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService extends AbstractService<User>{

    public UserService() {
        super(User.class);
    }
     public List<User> findAll()
    {
        return super.findAll("User.findAll");
    }
    
}
