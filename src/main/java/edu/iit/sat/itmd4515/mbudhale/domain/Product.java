package edu.iit.sat.itmd4515.mbudhale.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name="product.findAll",query="select p from Product p")
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;
    @Column(name = "PRODUCT_NAME")
    @NotBlank(message = "Product name is required")
    private String productName;
    
    @Column(name = "PRODUCT_UNIT")
    @NotBlank(message = "Product Unit is required")
    private String productUnit;
    
    @Column(name = "PRODUCT_COST")
    @NotNull(message = "Product Cost is required")
    private Long cost;
    
    @Column(name = "PRODUCT_PRICE")
    @NotNull(message = "Product Price is required")
    private Long price;

    @Column(name = "PRODUCT_BRAND")
    @NotBlank(message = "Product Brand is required")
    private String productBrand;

    
    @Column(name = "PRODUCT_CATEGORY")
    @NotBlank(message = "Product Category is required")
    private String productCategory;

    @ManyToMany(mappedBy = "orderItems")
    private Set<AllOrder> orders = new HashSet<>();

    @OneToOne(mappedBy = "product")
    private Inventory inventory;

    public Product() {
    }

    public Product(String productName, String productUnit, Long cost, Long price, String productBrand, String productCategory) {
        this.productName = productName;
        this.productUnit = productUnit;
        this.cost = cost;
        this.price = price;
        this.productBrand = productBrand;
        this.productCategory = productCategory;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id == null || other.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Set<AllOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<AllOrder> orders) {
        this.orders = orders;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", productName=" + productName + ", productUnit=" + productUnit + ", cost=" + cost + ", price=" + price + '}';
    }

   

}
