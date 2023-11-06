package edu.iit.sat.itmd4515.mbudhale.domain;

import edu.iit.sat.itmd4515.mbudhale.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Account.findAll", query = "select a from Account a")
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USERNAME", unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private AccountType type;

    @Column(name = "NAME", nullable = false, unique = true)
    @NotBlank(message = "Company name is required")
    private String company_Name;

    @Column(name = "EMAIL", nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "CONTACT", nullable = false)
    @NotBlank(message = "Phone number is required")
    //@Size(min = 10, max = 15)
    private String phone;

    @Column(name = "ADDRESS", nullable = false)
    @NotBlank(message = "Address is required")
    private String address;

    @Column(name = "ADDED_DATE", nullable = false)
    @NotNull(message = "Created date is required")
    private LocalDate created_Date;

    @OneToMany(mappedBy = "acc")
    private List<AllOrder> orderslist = new ArrayList<>();

    //Checking if Account Type is customer or Employee
    public boolean isValidAccount() {
        return AccountType.CUSTOMER.equals(type) || AccountType.EMPLOYEE.equals(type);
    }

    public Account() {
    }

    public Account(AccountType type, String companyName, String email, String phone, String address, LocalDate createdDate) {
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
        final Account other = (Account) obj;

        if (this.id == null || other.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    public List<AllOrder> getOrders() {
        return orderslist;
    }

    public void setOrders(List<AllOrder> orders) {
        this.orderslist = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
