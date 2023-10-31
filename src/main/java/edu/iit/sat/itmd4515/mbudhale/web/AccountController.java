package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.service.AccountService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class AccountController {
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

    public String demoAction() {
        LOG.info("In AccountController.demoAction Method invoked!!" + account.toString());
        return "confirmation.xhtml";
    }

    public AccountType[] getAllAccountType() {
        return AccountType.values();
    }
    public String saveAccount()
    {
        LOG.info("in AccountController.saveAccount"+account.toString());
        accountSvc.create(account);
        
        LOG.info("in AccountController.saveAccount after EJV call"+account.toString());
        return "confirmation.xhtml";
        
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
