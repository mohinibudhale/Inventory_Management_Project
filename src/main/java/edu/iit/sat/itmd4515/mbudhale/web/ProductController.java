
package edu.iit.sat.itmd4515.mbudhale.web;

import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ProductController {

    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());

    private Product product;

    public ProductController() {
    }

    @PostConstruct
    private void postConstruct() {
        product = new Product();
        LOG.info("In ProductController.postConstruct Method" );
    }

    public String demoAction() {
        LOG.info("In ProductController.demoAction Method invoked!!"+ product.toString());
        return "confirmation.xhtml";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
