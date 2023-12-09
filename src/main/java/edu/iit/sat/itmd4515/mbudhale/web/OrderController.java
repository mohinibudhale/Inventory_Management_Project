package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.domain.AllOrder;
import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.service.AccountService;
import edu.iit.sat.itmd4515.mbudhale.service.OrderService;
import edu.iit.sat.itmd4515.mbudhale.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class OrderController {

    private static final Logger LOG = Logger.getLogger(OrderController.class.getName());

    @Inject
    private OrderService orderService;
    @Inject
    private ProductService productService;
    @Inject
    private AccountService accSvc;
    @Inject
    LoginController loginController;
    private Long price;
    private Product selectedProduct;
    private Long quantity;
    private List<Product> productList;
    private String productBrand;
    private String productCategory;
    private AllOrder selectedOrder;
    private Long id;
    private String productUnit;
    private Product product;
    private Long totalPrice;
    private List<AllOrder> ordersList;
    private Account account;

    public OrderController() {
    }

    @PostConstruct
    public void postConstruct() {
        LOG.info("OrderController initialized");
        account = accSvc.findByUsername(loginController.getCurrentUser());
        ordersList = orderService.findById(account.getId());

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String orderIdParam = externalContext.getRequestParameterMap().get("orderId");

        if (orderIdParam != null) {
            Long orderId = Long.parseLong(orderIdParam);
            loadSelectedOrder(orderId);
        }
    }

    public String loadSelectedOrder(Long orderId) {
        LOG.info("In loadSelectedOrder ");
        selectedOrder = orderService.findOrderById(orderId);
        LOG.info("In " + selectedOrder.toString());

        if (selectedOrder == null) {
            productList = selectedOrder.getOrderItems();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Order not found."));
            
        }
        return "/customer/welcome.xhtml";
    }

    public String getCalculateTotalPrice() {
        if (selectedProduct != null && quantity != null && price != null) {
            return String.valueOf(quantity * price);
        }
        return "0";
    }

    public List<Product> completeProduct(String query) {
        LOG.info("In Complete method");
        productList = productService.findAll();
        String queryLowerCase = query.toLowerCase();

        return productList.stream()
                .filter(product -> product.getProductName().toLowerCase().startsWith(queryLowerCase))
                .collect(Collectors.toList());
    }

    public String placeOrder() {
        this.totalPrice = getSelectedProduct().getPrice() * quantity;
        LOG.info("total price " + totalPrice);
        LOG.info("In placeOrder with no of products");

        try {
            Account acc = accSvc.findByUsername(loginController.getCurrentUser());
            AllOrder order = new AllOrder();

            if (selectedProduct == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select a product."));
                return null;
            }

            if (quantity == null || quantity <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please enter a valid quantity."));
                return null;
            }
            order.setOrderType("Sales Order");
            order.setOrderPrice(totalPrice);
            order.setOrderBalance(totalPrice);
            order.setOrderDate(LocalDate.now());
            order.setQuantity((long) quantity);
            order.setAcc(acc);
            orderService.create(order);
            LOG.log(Level.INFO, "Success", "Order placed successfully.");

            return "/customer/welcome.xhtml";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error placing the order. Please try again."));
            LOG.log(Level.SEVERE, "Error placing the order", e);
            return null;
        }
    }

    public Product getSelectedProduct() {
        LOG.info("getSelectedProduct getting called");
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void onProductSelect() {
        LOG.info("onProductSelect getting called");
        if (selectedProduct != null) {
            selectedProduct = productService.findById(selectedProduct.getId());
            this.price = selectedProduct.getPrice();
            this.productBrand = selectedProduct.getProductBrand();
            this.productCategory = selectedProduct.getProductCategory();
            this.productUnit = selectedProduct.getProductUnit();
            selectedProduct.setPrice(selectedProduct.getPrice());
        } else {
            this.price = null;
            this.productBrand = null;
            this.productCategory = null;
        }
    }

    public void updateTotalPrice() {
        this.price = getSelectedProduct().getPrice();
        if (quantity != null && price != null) {
            totalPrice = quantity * price;
        } else {
            totalPrice = 0L;
        }
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public AllOrder getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(AllOrder selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public String cancelOrder() {
        try {
            LOG.info("In cancelOrder");

            if (selectedOrder == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select an order to cancel."));
                return null;
            }

            Account acc = selectedOrder.getAcc();
            if (acc != null) {
                acc.getOrders().remove(selectedOrder);
            }

            orderService.cancelOrder(selectedOrder);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Order canceled successfully."));
            return "/customer/welcome.xhtml";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error canceling the order. Please try again."));
            LOG.log(Level.SEVERE, "Error canceling the order", e);
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AllOrder> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<AllOrder> ordersList) {
        this.ordersList = ordersList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
