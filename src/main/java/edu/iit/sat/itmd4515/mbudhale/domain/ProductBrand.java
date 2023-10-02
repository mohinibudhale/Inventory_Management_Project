package edu.iit.sat.itmd4515.mbudhale.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "PRODUCT_BRAND")
public class ProductBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_BRAND_ID")
    private Long id;
    @Column(name = "BRAND_NAME")
    private String brandName;
    @Column(name = "BRAND_DESC")
    private String brandDesc;


    public ProductBrand() {
    }

    public ProductBrand( String brandName, String brandDesc) {
        this.brandName = brandName;
        this.brandDesc = brandDesc;
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
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final ProductBrand other = (ProductBrand) obj;
        if (this.id == null || other.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    @Override
    public String toString() {
        return "ProductBrand{" + "id=" + id + ", brandName=" + brandName + ", brandDesc=" + brandDesc + '}';
    }

}
