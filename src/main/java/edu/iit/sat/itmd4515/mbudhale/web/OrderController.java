package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.AllOrder;
import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.service.OrderService;
import edu.iit.sat.itmd4515.mbudhale.service.ProductService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class OrderController {

    @Inject
    private OrderService orderService;
    @Inject
    private ProductService productService;
    private Long price;
    private Product selectedProduct;
    private Long quantity;
    private List<Product> productList;
    private String searchProduct;

    public OrderController() {
    }

    public String getCalculateTotalPrice() {
        if (selectedProduct != null && quantity != null && price != null) {
            return String.valueOf(quantity * price);
        }
        return "0";
    }

    public void searchProducts() {
        try {
            if (searchProduct != null && !searchProduct.trim().isEmpty()) {
                productList = productService.searchProducts(searchProduct.trim());
            } else {
                // If search text is empty, you may want to return all products or handle it differently
                productList = productService.findAll();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error searching products. Please try again."));
            e.printStackTrace();
        }
    }

    public void placeOrder() {
        try {

            if (selectedProduct == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select a product."));
                return;
            }

            if (quantity <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please enter a valid quantity."));
                return;
            }

            AllOrder order = new AllOrder();
            order.setOrderType("CUSTOMER");
            order.setOrderPrice(price * quantity);
            order.setOrderBalance(0L);
            order.setOrderDate(LocalDate.now());
            order.setQuantity((long) quantity);

            orderService.create(order);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Order placed successfully."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error placing the order. Please try again."));
            e.printStackTrace();
        }
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
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

    public String getSearchProduct() {
        return searchProduct;
    }

    public void setSearchProduct(String searchProduct) {
        this.searchProduct = searchProduct;
    }
}
