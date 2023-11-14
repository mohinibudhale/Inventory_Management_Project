
package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.service.AccountService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class CustomerWelcomeController {

    @Inject LoginController loginController;
    @EJB AccountService accountSvc;
    
    private static final Logger LOG = Logger.getLogger(CustomerWelcomeController.class.getName());
    private Account customer;

    public CustomerWelcomeController() {
    }
    @PostConstruct
    private void postConstruct() {
        LOG.info("in CustomerWelcomeController.postConstruct");
        customer = accountSvc.findByUsername(loginController.getCurrentUser());
        LOG.info("Leaving CustomerWelcomeController.postConstruct with"+ customer.toString());
    }
    public Account getCustomer() {
        return customer;
    }
    public void setCustomer(Account customer) {
        this.customer = customer;
    }
}
