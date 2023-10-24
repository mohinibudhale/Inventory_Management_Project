
package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.ProductCategory;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class CategoryService extends AbstractService<ProductCategory>{
    
    public CategoryService() {
        super(ProductCategory.class);
    }
    public List<ProductCategory> findAll() {
        return super.findAll("ProductCategory.findAll");
    }
    
}
