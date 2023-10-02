
package edu.iit.sat.itmd4515.mbudhale.lab3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

//making address an enity as part of lab 5
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Address_ID")
    private Integer id;
    @NotNull
    private String address;    
    @NotNull
    private String address2;    
    @NotNull
    private String district; 
    @Positive
    @NotNull
    private Integer cityId;
    @Positive
    @NotNull
    private String postalCode;     
    @Positive
    @NotNull
    private String phone;    
    
    private String location;

    public Address() {
    }

    public Address(String address, String address2, String district, Integer cityId, String postalCode, String phone, String location) {
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.location = location;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public Integer getcityId() {
        return cityId;
    }
    public void setcityId(Integer city_id) {
        this.cityId = city_id;
    }
    public String getpostalCode() {
        return postalCode;
    }
    public void setpostalCode(String postal_code) {
        this.postalCode = postal_code;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Address{" + "address=" + address + ", address2=" + address2 + ", district=" + district + ", cityId=" + cityId + ", postalCode=" + postalCode + ", phone=" + phone + ", location=" + location + '}';
    }


    
}
