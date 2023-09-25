package edu.iit.sat.itmd4515.mbudhale.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Accounts")
public class Accounts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE", nullable=false, unique=true)
    private AccountType type; 
    
    @Column(name = "ACCOUNT_COMPANY_NAME",nullable=false,unique=true)
    @NotBlank(message = "Company name is required")
    private String company_Name;
    
    @Column(name = "ACCOUNT_EMAIL",nullable=false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @Column(name = "ACCOUNT_PHONE",nullable=false)
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phone;
    
    @Column(name = "ACCOUNT_ADDRESS",nullable=false) 
    @NotBlank(message = "Address is required")
    private String address;
    
    @Column(name = "ACCOUNT_CREATED_DATE",nullable=false)
    @NotNull(message = "Created date is required")
    private LocalDate created_Date;

    public Accounts() {
    }

    public Accounts(AccountType type, String companyName, String email, String phone, String address, LocalDate createdDate) {
        this.type = type;
        this.company_Name = companyName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.created_Date = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public void setCompany_Name(String companyName) {
        this.company_Name = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(LocalDate createdDate) {
        this.created_Date = createdDate;
    }

    @Override
    public String toString() {
        return "Accounts{" + "id=" + id + ", type=" + type + ", companyName=" + company_Name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", createdDate=" + created_Date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Accounts other = (Accounts) obj;
        
        if(this.id== null || other.id==null)
        {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }


    
}
