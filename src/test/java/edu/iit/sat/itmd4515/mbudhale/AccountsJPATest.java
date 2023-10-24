package edu.iit.sat.itmd4515.mbudhale;

import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import edu.iit.sat.itmd4515.mbudhale.domain.AllOrder;
import edu.iit.sat.itmd4515.mbudhale.domain.Inventory;
import edu.iit.sat.itmd4515.mbudhale.domain.Product;
import edu.iit.sat.itmd4515.mbudhale.domain.ProductBrand;
import edu.iit.sat.itmd4515.mbudhale.domain.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountsJPATest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    @BeforeAll
    public static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    @BeforeEach
    public void beforeEach() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        Account a = new Account(AccountType.VENDOR, "Maxwell Street Market", "Maxwell@gmail.com", "773-769-1111", "5039 N Broadway, Chicago Illinois 60640", LocalDate.now());
        tx.begin();
        em.persist(a);
        tx.commit();

    }

    @Test
    public void createTest() {
        Account a = new Account(AccountType.CUSTOMER, "xyz", "CurryHouse@gmail.com", "773-769-1132", "1200 N Broadway, Chicago Illinois 60614", LocalDate.now());
        tx.begin();
        em.persist(a);
        tx.commit();
        assertTrue(a.getId() > 0);
        assertEquals("xyz", a.getCompany_Name());
        tx.begin();
        em.remove(a);
        tx.commit();
    }

    @Test
    public void readTest() {
        Account a = em.
                createQuery("select a "
                        + "from Account a "
                        + "where a.company_Name='Maxwell Street Market'", Account.class)
                .getSingleResult();
        assertNotNull(a);
        assertTrue(a.getId() > 0);
        assertEquals("Maxwell Street Market", a.getCompany_Name());
    }

    @Test
    public void updateTest() {
        Account a = em.
                createQuery("select a "
                        + "from Account a "
                        + "where a.company_Name='Maxwell Street Market'", Account.class)
                .getSingleResult();
        assertNotNull(a);
        assertTrue(a.getId() > 0);
        assertEquals("Maxwell Street Market", a.getCompany_Name());
        a.setType(AccountType.CUSTOMER);
        tx.begin();
        em.merge(a);
        tx.commit();
        Account DataReadFromDB = em.find(Account.class, a.getId());
        assertEquals(AccountType.CUSTOMER, DataReadFromDB.getType());
    }

    @Test
    public void deleteTest() {
        tx.begin();
        Account newA = new Account(AccountType.CUSTOMER, "Nepal House", "NepalHouse@gmail.com", "773-769-1132", "1200 N SouthB, Chicago Illinois 60616", LocalDate.now());
        em.persist(newA);
        Account ar = em.
                createQuery("select newA "
                        + "from Account newA "
                        + "where newA.company_Name='Nepal House'", Account.class)
                .getSingleResult();
        tx.commit();
        tx.begin();
        em.remove(ar);
        tx.commit();
        Account TryReadbackfromDB = em.
                createQuery("select newA "
                        + "from Account newA "
                        + "where newA.company_Name='Nepal House'", Account.class)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull(TryReadbackfromDB);

    }
    // Testing UniDirectional Relationship
    @Test
    public void testUnidirectionalRelationship() {
        tx.begin();
        ProductBrand productBrand = new ProductBrand("Brand 2", "Brand Description 1");
        em.persist(productBrand);
        ProductCategory productCategory = new ProductCategory("Category 2", "Product Category Description 1");
        em.persist(productCategory);
        Product product = new Product("Product 1", "Unit", 100L, 200L, productBrand, productCategory);
        em.persist(product);
        Inventory inventory = new Inventory(10L, 2000L, product);
        em.persist(inventory);
        tx.commit();
        inventory = em.find(Inventory.class, inventory.getId());
        assertNotNull(inventory);
        product = inventory.getProduct();
        assertNotNull(product);
        assertEquals("Product 1", product.getProductName());
        tx.begin();
        em.remove(inventory);
        em.remove(product);
        em.remove(product.getProductBrand());
        em.remove(product.getProductCategory());
        tx.commit();

    }
    // Testing BiDirectional Relationship
    @Test
    public void biDirectionalRelationshipTest() {
        tx.begin();
        ProductBrand productBrand = new ProductBrand("Brand 1", "Brand Description 1");
        em.persist(productBrand);
        ProductCategory productCategory = new ProductCategory("Category 1", "Product Category Description 1");
        em.persist(productCategory);
        Product product = new Product("Product 1", "Unit", 100L, 200L, productBrand, productCategory);
        em.persist(product);
        Account account = new Account(AccountType.CUSTOMER, "Customer Name", "customer@email.com", "123-456-7890", "Customer Address", LocalDate.now());
        em.persist(account);
        AllOrder order = new AllOrder("Order Type", 100L, 50L, LocalDate.now(), account);
        em.persist(order);
        tx.commit();

        order.addProduct(product);

        assertTrue(order.getOrderItems().contains(product));
        assertEquals(1, order.getOrderItems().size());

        assertTrue(product.getOrders().contains(order));
        assertEquals(1, product.getOrders().size());
        
        order.removeProduct(product);

        assertFalse(order.getOrderItems().contains(product));
        assertEquals(0, order.getOrderItems().size());

        assertFalse(product.getOrders().contains(order));
        assertEquals(0, product.getOrders().size());

        tx.begin();
        em.remove(order);
        em.remove(product);
        em.remove(product.getProductBrand());
        em.remove(product.getProductCategory());
        tx.commit();

    }

    @AfterEach
    public void afterEach() {

        Account a = em.
                createQuery("select a "
                        + "from Account a "
                        + "where a.company_Name='Maxwell Street Market'", Account.class
                )
                .getSingleResult();
        tx.begin();
        em.remove(a);
        tx.commit();
    }

    @AfterAll
    public static void afterAll() {
    }

}
