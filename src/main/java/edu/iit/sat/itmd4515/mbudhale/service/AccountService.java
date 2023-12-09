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

    public List<Account> findAll() {
        return super.findAll("Account.findAll");
    }

    public Account findByUsername(String username) {
        return em.createNamedQuery("Account.findByUsername", Account.class)
                .setParameter("uname", username)
                .getSingleResult();
    }
    
     public Account getAccountById(Long id) {
        return em.createNamedQuery("Account.getAccountById", Account.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void updateAccount(Account a) {
        Account managedAccRef = em.getReference(Account.class, a.getId());
        managedAccRef.setCompany_Name(a.getCompany_Name());
        managedAccRef.setType(a.getType());
        managedAccRef.setEmail(a.getEmail());
        managedAccRef.setPhone(a.getPhone());
        managedAccRef.setAddress(a.getAddress());
        managedAccRef.setCreated_Date(a.getCreated_Date());
        em.merge(managedAccRef);

    }
   
    public void deleteAccount(Account a) {
        Account managedAccRef = em.find(Account.class, a.getId());
        if (managedAccRef != null) {            
            em.remove(managedAccRef);
        } else {
            System.out.println("Account with ID " + a.getId() + " not found for deletion.");
        }
    }

}
