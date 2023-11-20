package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ProductController {

    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());

    private Product product;
    @Inject
    ProductService productService;
   
    public ProductController() {
    }

    public String saveProduct() {
        try {
            LOG.info("In ProductController.saveProduct Method: " + product.toString());

            if (product.getProductName() == null || product.getProductName().trim().isEmpty()
                    || product.getProductUnit() == null || product.getProductUnit().trim().isEmpty()
                    || product.getCost() == null || product.getPrice() == null) {

                LOG.warning("Invalid input: Please fill in all required fields.");

                return "/errors/error.xhtml";
            }

            productService.create(product);

            LOG.info("Product saved successfully");

            product = new Product();

            return "/admin/welcome.xhtml";

        } catch (Exception e) {
            LOG.severe("Error saving product: " + e.getMessage());
            return "/errors/error.xhtml";
        }
    }

   
    @PostConstruct
    private void postConstruct() {
        product = new Product();
        LOG.info("In ProductController.postConstruct Method");
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
