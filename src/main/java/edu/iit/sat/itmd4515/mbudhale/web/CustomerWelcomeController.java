package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.domain.AllOrder;
import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.service.AccountService;
import edu.iit.sat.itmd4515.mbudhale.service.OrderService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class CustomerWelcomeController {

    @Inject
    LoginController loginController;

    @EJB
    AccountService accountSvc;
    @EJB
    private OrderService orderService;
    private AllOrder selectedOrder;

    private List<AllOrder> ordersList;

    private List<Product> productList;

    private static final Logger LOG = Logger.getLogger(CustomerWelcomeController.class.getName());
    private Account account;

    public CustomerWelcomeController() {

    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("in CustomerWelcomeController.postConstruct");
        LOG.info("in CustomerWelcomeController current user is " + loginController.getCurrentUser());
        account = accountSvc.findByUsername(loginController.getCurrentUser());
        ordersList = orderService.findById(account.getId());

    }

    public String loadSelectedOrder(Long orderId) {
        LOG.info("in CustomerWelcomeController.loadSelectedOrder");
        selectedOrder = orderService.findOrderById(orderId);
        if (selectedOrder == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Order not found."));

        }
        return "/customer/UpdateOrder.xhtml?faces-redirect=true";
    }

    public List<AllOrder> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<AllOrder> ordersList) {
        this.ordersList = ordersList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void cancelOrder(AllOrder order) {
        // Implement logic to cancel the order
        orderService.cancelOrder(order);
        LOG.info("Order canceled: " + order.getId());
        // Refresh the orders list after canceling the order
        ordersList = orderService.findById(account.getId());
    }

    public AllOrder getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(AllOrder selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

}
