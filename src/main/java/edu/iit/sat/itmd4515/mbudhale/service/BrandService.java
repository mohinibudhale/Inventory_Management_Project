package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.ProductBrand;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class BrandService extends AbstractService<ProductBrand> {

    public BrandService() {
        super(ProductBrand.class);
    }

    public List<ProductBrand> findAll() {
        return super.findAll("ProductBrand.findAll");
    }

}
