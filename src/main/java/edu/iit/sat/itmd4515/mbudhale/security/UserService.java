package edu.iit.sat.itmd4515.mbudhale.security;

import edu.iit.sat.itmd4515.mbudhale.service.AbstractService;
import edu.iit.sat.itmd4515.mbudhale.service.AccountService;
import edu.iit.sat.itmd4515.mbudhale.service.StartupSingletonService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserService extends AbstractService<User> {

    @EJB
    AccountService accSvc;
    @EJB
    StartupSingletonService ssSvc;
    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    public UserService() {
        super(User.class);
    }

    public List<User> findAll() {
        return super.findAll("User.findAll");
    }
    
     public User findByUsername(String username) {         
         return em.find(User.class, username);
    }

    public void assignGroupToUser(User user) {
        Group groupToAdd = null;
        groupToAdd = ssSvc.getCustomerGroup();
        
        if (groupToAdd != null) {
            LOG.info("In group to add " + groupToAdd);
            user.addGroup(groupToAdd);
            //em.merge(user);
            LOG.info(user.getGroups().toString());
        }
    }
    
    public void deleteUser(User user) {
        User managedAccRef = em.find(User.class, user.getUserName());
        if (managedAccRef != null) {
            
            managedAccRef.getGroups().forEach(group -> {
                group.getUsers().remove(user);
            });
            
            em.remove(managedAccRef);
            
        } else {
            System.out.println("Account with ID " + user.getUserName() + " not found for deletion.");
        }
    }


}
