
package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

@Stateless
@Named
public class AccountService extends AbstractService<Account> {

    public AccountService() {
        super(Account.class);
    }
    public List<Account> findAll()
    {
        return super.findAll("Account.findAll");
    }
    
    public Account findByUsername(String username)
    {
        return em.createNamedQuery("Account.findByUsername",Account.class)
                .setParameter("uname", username)
                .getSingleResult();
    }
    
}
