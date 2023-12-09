package edu.iit.sat.itmd4515.mbudhale.web;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.service.ProductService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@RequestScoped
@FacesConverter("productConverter")
public class ProductConverter implements Converter {

    
    @Inject
    ProductService productService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            Long productId = Long.valueOf(value);
            if (productService != null) {
                return productService.findById(productId);
            } else {
                throw new IllegalStateException("ProductService is not injected.");
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid product ID: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Product)) {
            return null;
        }

        return String.valueOf(((Product) value).getId());
    }
}
