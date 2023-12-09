package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Named
public class ProductService extends AbstractService<Product> {

    private static final Logger LOG = Logger.getLogger(ProductService.class.getName());

    public ProductService() {
        super(Product.class);
    }

    public void updateProduct(Product p) {
        try {
            LOG.info("In ProductService.update Method: " + p.toString());

            Long productId = p.getId();
            LOG.info(p.getId().toString());

            if (productId == null) {
                LOG.warning("Product ID is null. Unable to perform update without a valid identifier.");
                return;
            }

            Product managedProductRef = em.find(Product.class, productId);

            if (managedProductRef == null) {
                LOG.warning("Product with ID " + productId + " not found in the database.");
                return;
            }

            managedProductRef.setProductName(p.getProductName());
            managedProductRef.setCost(p.getCost());
            managedProductRef.setPrice(p.getPrice());
            managedProductRef.setProductBrand(p.getProductBrand());
            managedProductRef.setProductCategory(p.getProductCategory());
            managedProductRef.setProductUnit(p.getProductUnit());

            em.merge(managedProductRef);

            LOG.info("Product updated successfully");

        } catch (Exception e) {
            LOG.info("Error updating product");
        }
    }

    public void delete(Product p) {
        Product managedProductRef = em.find(Product.class, p.getId());
        if (managedProductRef != null) {
            em.remove(managedProductRef);
        } else {
            System.out.println("Product with ID " + p.getId() + " not found for deletion.");
        }
    }

    public List<Product> searchProducts(String searchTerm) {

        return em.createQuery("SELECT p FROM Product p WHERE p.productName LIKE :searchTerm", Product.class)
                .setParameter("searchTerm", "%" + searchTerm + "%")
                .getResultList();
    }

    public List<Product> findAll() {
        return em.createNamedQuery("product.findAll", Product.class).getResultList();
    }

    public Product findById(Long id) {       
        return em.createNamedQuery("Product.findProductById", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }
     

}
