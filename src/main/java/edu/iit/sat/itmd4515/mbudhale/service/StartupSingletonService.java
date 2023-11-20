package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.domain.AllOrder;
import edu.iit.sat.itmd4515.mbudhale.domain.Inventory;
import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.security.Group;
import edu.iit.sat.itmd4515.mbudhale.security.GroupService;
import edu.iit.sat.itmd4515.mbudhale.security.User;
import edu.iit.sat.itmd4515.mbudhale.security.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.time.LocalDate;
import java.util.logging.Logger;

@Startup
@Singleton
public class StartupSingletonService {

    private static final Logger LOG = Logger.getLogger(StartupSingletonService.class.getName());
    @EJB
    ProductService productSvc;
    @EJB
    OrderService orderSvc;
    @EJB
    AccountService accountSvc;
  
    @EJB
    InventoryService inventorySvc;
    @EJB
    GroupService groupSvc;
    @EJB
    UserService userSvc;

    public StartupSingletonService() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("StartupSingletonService.postConstruct");

        //Security Domain Initialization
        Group customerGroup = new Group("CUSTOMER_GROUP", "This is customer group in security realm representing customers");
        Group employeeGroup = new Group("EMPLOYEE_GROUP", "This is employee group in security realm representing employees");
        Group adminGroup = new Group("ADMIN_GROUP", "This is admin group in security realm representing administrative users");

        groupSvc.create(customerGroup);
        groupSvc.create(employeeGroup);
        groupSvc.create(adminGroup);

        User employee1 = new User("employee1", "employee1", true);
        userSvc.create(employee1);
        employee1.addGroup(employeeGroup);
        employee1.addGroup(adminGroup);
        
        User employee2 = new User("employee2", "employee2", true);
        userSvc.create(employee2);
        employee2.addGroup(employeeGroup);
        
        User customer1 = new User("customer1", "customer1", true);
        userSvc.create(customer1);
        customer1.addGroup(customerGroup);
        
        User customer2 = new User("customer2", "customer2", true);
        userSvc.create(customer2);
        customer2.addGroup(customerGroup);
        
        
        User admin = new User("admin", "admin", true);
        userSvc.create(admin);
        admin.addGroup(employeeGroup);
        admin.addGroup(adminGroup);

        Account a1 = new Account(AccountType.CUSTOMER, "ABCD", "ABCD@test.com", "8727564782", "500 E 32nd St 60616", LocalDate.now());
        if (a1.isValidAccount()) {            
            a1.setUser(customer1);
        }
        Account a2 = new Account(AccountType.VENDOR, "Rich Factory", "RichF@test.com", "2347564782", "15L E 32nd St 60616", LocalDate.now());
        if (a2.isValidAccount()) {            
            a2.setUser(customer2);
        }
        Account a3 = new Account(AccountType.EMPLOYEE, "emp1", "emp1@test.com", "1234567892", "15L E 32nd St 60616", LocalDate.now());
        if (a3.isValidAccount()) {            
            a3.setUser(employee1);
        }
        accountSvc.create(a1);
        accountSvc.create(a2);
        accountSvc.create(a3);
        
        Product p1 = new Product("Whole Milk", "l", 5L, 10L, "BB1", "CC1");       
        Product p2 = new Product("Lays Chips", "LB", 10L, 20L, "BB2", "CC2");       
        Product p3 = new Product("Pretzels", "LB", 30L, 35L, "BB3", "CC3");
       
        productSvc.create(p1);
        productSvc.create(p2);
        productSvc.create(p3);

        Inventory i1 = new Inventory(100L, 1000L, null);
        i1.setProduct(p1);
        Inventory i2 = new Inventory(50L, 1000L, null);
        i2.setProduct(p2);
        Inventory i3 = new Inventory(10L, 300L, null);
        i3.setProduct(p3);

        inventorySvc.create(i1);
        inventorySvc.create(i2);
        inventorySvc.create(i3);

        AllOrder o1 = new AllOrder("purchase order", 100L, 100L, LocalDate.now(), null);
        o1.createOrder(a2);
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p3);

        AllOrder o2 = new AllOrder("Sales order", 150L, 150L, LocalDate.now(), null);
        o2.createOrder(a1);
        o2.addProduct(p2);
        o2.addProduct(p3);
        orderSvc.create(o1);
        orderSvc.create(o2);
        //Output
        LOG.info("\n" + "==============Account JPA Relationship with Order & Order JPA Relation with Products(O-M & M-M)===========================");
        for (Account a : accountSvc.findAll()) {
            LOG.info(a.toString());
            for (AllOrder o : a.getOrders()) {
                LOG.info("\t" + o.toString());
                LOG.info("=================Order JPA Relationship with Product==============");
                LOG.info("\t" + o.toString());
                for (Product p : o.getOrderItems()) {
                    LOG.info("\t" + p.toString());
                }
            }
        }
        LOG.info("\n" + "==============Inventory JPA Relationship with Product(O-O)================================");
        for (Inventory i : inventorySvc.findAll()) {
            LOG.info("\t" + i.toString());
            LOG.info("\t" + i.getProduct());
        }
        LOG.info("\n" + "==============Product JPA Relationship with ProductBrand & Category(O-O)================================");
        for (Product p : productSvc.findAll()) {
            LOG.info("\t" + p.toString());
            LOG.info("\t" + p.getProductBrand());
            LOG.info("\t" + p.getProductCategory() + "\n");
        }
    }

}
