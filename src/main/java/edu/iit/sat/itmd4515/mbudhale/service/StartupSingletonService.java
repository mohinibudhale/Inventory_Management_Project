package edu.iit.sat.itmd4515.mbudhale.service;

import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.domain.AllOrder;
import edu.iit.sat.itmd4515.mbudhale.domain.Inventory;
import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.domain.ProductBrand;
import edu.iit.sat.itmd4515.mbudhale.domain.ProductCategory;
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
    BrandService brandSvc;
    @EJB
    CategoryService categorySvc;
    @EJB
    InventoryService inventorySvc;

    public StartupSingletonService() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("StartupSingletonService.postConstruct");

        Account a1 = new Account(AccountType.CUSTOMER, "ABCD", "ABCD@test.com", "8727564782", "500 E 32nd St 60616", LocalDate.now());
        Account a2 = new Account(AccountType.VENDOR, "Rich Factory", "RichF@test.com", "2347564782", "15L E 32nd St 60616", LocalDate.now());
        accountSvc.create(a1);
        accountSvc.create(a2);

        ProductBrand pb1 = new ProductBrand("Lays", "Lays Chips");
        ProductBrand pb2 = new ProductBrand("Friendly Farm", "Whole Milk");
        ProductBrand pb3 = new ProductBrand("Rold Gold", "Rold Gold tiny twist");
        brandSvc.create(pb1);
        brandSvc.create(pb2);
        brandSvc.create(pb3);

        ProductCategory pc1 = new ProductCategory("Savory products", "Snacks,Chips, pretzels");
        ProductCategory pc2 = new ProductCategory("Dairy products", "whole milk, fat milk, low fat milk, fat free milk");
        categorySvc.create(pc1);
        categorySvc.create(pc2);

        Product p1 = new Product("Whole Milk", "l", 5L, 10L, null, null);
        p1.setProductBrand(pb2);
        p1.setProductCategory(pc2);
        Product p2 = new Product("Lays Chips", "LB", 10L, 20L, null, null);
        p2.setProductBrand(pb1);
        p2.setProductCategory(pc1);
        Product p3 = new Product("Pretzels", "LB", 30L, 35L, null, null);
        p3.setProductBrand(pb3);
        p3.setProductCategory(pc1);

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
        LOG.info("\n"+"==============Account JPA Relationship with Order & Order JPA Relation with Products(O-M & M-M)===========================");
        for (Account a : accountSvc.findAll()) {
            LOG.info(a.toString());            
            for (AllOrder o : a.getOrders()) {
                LOG.info( "\t" + o.toString());
                LOG.info("=================Order JPA Relationship with Product==============");
                LOG.info( "\t" +o.toString());
                for (Product p : o.getOrderItems()) {
                    LOG.info("\t" + p.toString());
                }
            }
        }
        LOG.info("\n"+"==============Inventory JPA Relationship with Product(O-O)================================");
        for (Inventory i : inventorySvc.findAll()) {
            LOG.info("\t" + i.toString());
            LOG.info("\t"+i.getProduct());     
        }
        LOG.info("\n"+"==============Product JPA Relationship with ProductBrand & Category(O-O)================================");
        for (Product p : productSvc.findAll()) {
            LOG.info("\t" + p.toString());
            LOG.info("\t"+p.getProductBrand());  
            LOG.info("\t"+p.getProductCategory()+"\n");  
        }
    }

}
