package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.service.AccountService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class AccountController {
    @Inject CustomerWelcomeController CWC;
    @EJB AccountService accountSvc;
    private static final Logger LOG = Logger.getLogger(AccountController.class.getName());
    private Account account;

    public AccountController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("in AccountController.postConstruct");
        account = new Account();
    }

    public AccountType[] getAllAccountType() {
        return AccountType.values();
    }
    public String saveAccount()
    {
        LOG.info("in AccountController.saveAccount"+account.toString());
        accountSvc.create(account);
        LOG.info("in AccountController.saveAccount after EJV call"+account.toString());
        //CWC.refreshAccount();        
        return "/admin/welcome.xhtml";
        
    }
    
    public String updateAccount()
    {
        LOG.info("in AccountController.updateAccount"+account.toString());
        accountSvc.updateAccount(account);            
        return "/admin/welcome.xhtml";
        
    }
    
    
    public String deleteAccount()
    {
        LOG.info("in AccountController.deleteAccount"+account.toString());
        accountSvc.deleteAccount(account);           
        return "/admin/welcome.xhtml";
        
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
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
