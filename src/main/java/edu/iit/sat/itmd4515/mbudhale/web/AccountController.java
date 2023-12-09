package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.security.User;
import edu.iit.sat.itmd4515.mbudhale.security.UserService;
import edu.iit.sat.itmd4515.mbudhale.service.AccountService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class AccountController {
    @Inject CustomerWelcomeController CWC;
    @EJB AccountService accountSvc;
    @EJB UserService userSvc;
    private static final Logger LOG = Logger.getLogger(AccountController.class.getName());
    private Account account;
    private User user;

    public AccountController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("in AccountController.postConstruct");
        account = new Account();
        user = new User();
    }

    public AccountType[] getAllAccountType() {
        return AccountType.values();
    }
    
    public String saveAccount()
    {
        userSvc.assignGroupToUser(user);
        user.setEnabled(true);
        userSvc.create(user);
        account.setUser(user);
        LOG.info("in AccountController.saveAccount"+account.toString());
        accountSvc.create(account); 
        LOG.info(user.toString()); 
       
        return "/admin/welcome.xhtml";        
    }
    
    public String updateAccount()
    {
        LOG.info("in AccountController.updateAccount"+account.toString());
        accountSvc.updateAccount(account);            
        return "/admin/welcome.xhtml";
        
    }
    
    public String deleteAccount() {
    try {
        
        Account existingAccount = accountSvc.getAccountById(account.getId());
        
        if (existingAccount != null) {
            User associatedUser = existingAccount.getUser();
            accountSvc.deleteAccount(existingAccount);
            
            if (associatedUser != null) {  
                
                User foundUser = userSvc.findByUsername(associatedUser.getUserName());
                
                 if (foundUser != null)
                 {                    
                     userSvc.deleteUser(foundUser);
                                         
                 }              
                  
            }
            LOG.info("in AccountController.deleteAccount" + account.toString());
            
            return "/admin/welcome.xhtml";
        } 
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account not found", null));
            return null;
        }
    } 
    catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error deleting account", null));
        e.printStackTrace();
        return null;
    }
}

    
    public String diplayAccountDetailsPage(Account a)
    {
        this.account = a;
        LOG.info("in AccountController.diplayAccountDetailsPage"+account.toString());        
        return "/admin/readAccount.xhtml";
    }
     public String diplayUpdateAccountPage(Account a)
    {
        this.account = a;
        LOG.info("in AccountController.diplayUpdateAccountPage"+account.toString());        
        return "/admin/updateAccount.xhtml";
    }
     
      public String diplayDeleteAccountPage(Account a)
    {
        this.account = a;
        LOG.info("in AccountController.diplayDeleteAccountPage"+account.toString());        
        return "/admin/deleteAccount.xhtml";
    }
      
    public String diplayCreateAccountPage(Account a)
    {
        this.account = a;
        LOG.info("in AccountController.diplayCreateAccountPage"+account.toString());        
        return "/admin/CreateAccount.xhtml";
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
