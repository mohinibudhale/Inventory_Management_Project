
package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class AccountService extends AbstractService<Account> {

    public AccountService() {
        super(Account.class);
    }
    public List<Account> findAll()
    {
        return super.findAll("Account.findAll");
    }
    
    
}
