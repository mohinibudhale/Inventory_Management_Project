package edu.iit.sat.itmd4515.mbudhale.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name="AllOrder.findAll",query="select o from AllOrder o")
@Table(name = "AllOrder")
public class AllOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;
    @Column(name = "ORDER_TYPE")
    private String orderType;
    @Column(name = "ORDER_PRICE")
    private Long orderPrice;
    @Column(name = "ORDER_BALANCE")
    private Long orderBalance;
    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;

    @ManyToMany
    @JoinTable(name = "ORDER_ITEMS",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Set<Product> orderItems = new HashSet<>();
    //owening side of bidirectional relationship
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account acc;      

    public AllOrder() {
    }

    public AllOrder(String orderType, Long orderPrice, Long orderBalance, LocalDate orderDate, Account acc) {
        this.orderType = orderType;
        this.orderPrice = orderPrice;
        this.orderBalance = orderBalance;
        this.orderDate = orderDate;
        this.acc = acc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final AllOrder other = (AllOrder) obj;
        if (this.id == null || other.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getOrderBalance() {
        return orderBalance;
    }

    public void setOrderBalance(Long orderBalance) {
        this.orderBalance = orderBalance;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Set<Product> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<Product> orderItems) {
        this.orderItems = orderItems;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    
    public void addProduct(Product product) {
        this.orderItems.add(product);
        product.getOrders().add(this);
    }

    public void removeProduct(Product product) {
        this.orderItems.remove(product);
        product.getOrders().remove(this);
    }

    public void createOrder(Account a){
        this.acc=a;  
        if(!a.getOrders().contains(this))
        {
            a.getOrders().add(this);
        }        
    }
    public void removeOrder(Account a)
    {
        this.acc=a;
        if(a.getOrders().contains(this))
        {
           a.getOrders().remove(this);
        }
        this.acc=null;
    }

    @Override
    public String toString() {
        return "AllOrder{" + "id=" + id + ", orderType=" + orderType + ", orderPrice=" + orderPrice + ", orderBalance=" + orderBalance + ", orderDate=" + orderDate + '}';
    }
 
}
