package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ProductController {

    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());

    private Product product;

    @EJB
    ProductService productService;

    public ProductController() {
    }

    @PostConstruct
    private void postConstruct() {
        product = new Product();
        LOG.info("In ProductController.postConstruct Method");
    }

    public String saveProduct() {
        try {
            LOG.info("In ProductController.saveProduct Method: " + product.toString());

            if (product.getProductName() == null || product.getProductName().trim().isEmpty()
                    || product.getProductUnit() == null || product.getProductUnit().trim().isEmpty()
                    || product.getCost() == null || product.getPrice() == null) {

                LOG.warning("Invalid input: Please fill in all required fields.");

                return "/errors/error.xhtml";
            } else {
                productService.create(product);
                LOG.info("Product saved successfully");
            }
            this.product = product;
            return "/admin/products.xhtml";

        } catch (Exception e) {
            LOG.severe("Error saving product: " + e.getMessage());
            return "/errors/error.xhtml";
        }
    }

    public String updateProduct() {
        LOG.info("In ProductController.updateProduct Method: " + product.toString());
        productService.updateProduct(product);
        return "/admin/products.xhtml";

    }

    public String deleteProduct() {
        try {
            LOG.info("In ProductController.deleteProduct Method: " + product.toString());

            Product existingProduct = productService.findById(product.getId());

            if (existingProduct != null) {
                productService.delete(existingProduct);

                LOG.info("Product deleted successfully");

                return "/admin/products.xhtml";
            } else {
                LOG.warning("Product not found");
                return null;
            }

        } catch (Exception e) {
            LOG.severe("Error deleting product: " + e.getMessage());
            return "/errors/error.xhtml";
        }
    }

    public String displayProductDetailsPage(Product p) {
        this.product = p;
        LOG.info("In ProductController.displayProductDetailsPage: " + product.toString());
        return "/admin/readProduct.xhtml";
    }

    public String displayUpdateProductPage(Product p) {
        this.product = p;
        LOG.info("In ProductController.displayUpdateProductPage: " + product.toString());
        return "/admin/updateProduct.xhtml";
    }

    public String displayDeleteProductPage(Product p) {
        this.product = p;
        LOG.info("In ProductController.displayDeleteProductPage: " + product.toString());
        return "/admin/deleteProduct.xhtml";
    }

    public String displayCreateProductPage(Product p) {
        this.product = p;
        LOG.info("In ProductController.displayCreateProductPage: " + product.toString());
        return "/admin/createProduct.xhtml";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
