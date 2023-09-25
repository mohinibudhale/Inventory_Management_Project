package edu.iit.sat.itmd4515.mbudhale;

import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.domain.Accounts;
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
        Accounts a = new Accounts(AccountType.VENDOR, "Maxwell Street Market", "Maxwell@gmail.com", "773-769-1111", "5039 N Broadway, Chicago Illinois 60640", LocalDate.now());
        tx.begin();
        em.persist(a);
        tx.commit();
    }

    @Test
    public void createTest() {
        Accounts a = new Accounts(AccountType.CUSTOMER, "Curry House", "CurryHouse@gmail.com", "773-769-1132", "1200 N Broadway, Chicago Illinois 60614", LocalDate.now());
        tx.begin();
        em.persist(a);
        tx.commit();
        assertTrue(a.getId() > 0);
        assertEquals("Curry House", a.getCompany_Name());
    }

    @Test
    public void readTest() {
        Accounts a = em.
                createQuery("select a "
                        + "from Accounts a "
                        + "where a.company_Name='Maxwell Street Market'", Accounts.class)
                .getSingleResult();
        assertNotNull(a);
        assertTrue(a.getId() > 0);
        assertEquals("Maxwell Street Market", a.getCompany_Name());
    }

    @Test
    public void updateTest() {
        Accounts a = em.
                createQuery("select a "
                        + "from Accounts a "
                        + "where a.company_Name='Maxwell Street Market'", Accounts.class)
                .getSingleResult();
        assertNotNull(a);
        assertTrue(a.getId() > 0);
        assertEquals("Maxwell Street Market", a.getCompany_Name());
        a.setType(AccountType.CUSTOMER);
        tx.begin();
        em.merge(a);
        tx.commit();
        Accounts DataReadFromDB = em.find(Accounts.class, a.getId());
        assertEquals(AccountType.CUSTOMER, DataReadFromDB.getType());
    }

    @Test
    public void deleteTest() {
        Accounts a = em.
                createQuery("select a "
                        + "from Accounts a "
                        + "where a.company_Name='Curry House'", Accounts.class)
                .getSingleResult();
        assertTrue(a.getId() > 0);
        assertEquals("Curry House", a.getCompany_Name());
        tx.begin();
        em.remove(a);
        tx.commit();
    }

    @AfterEach
    public void afterEach() {
        Accounts a = em.
                createQuery("select a "
                        + "from Accounts a "
                        + "where a.company_Name='Maxwell Street Market'", Accounts.class)
                .getSingleResult();
        tx.begin();
        em.remove(a);
        tx.commit();
    }

    @AfterAll
    public static void afterAll() {
    }

}
