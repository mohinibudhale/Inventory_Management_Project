package edu.iit.sat.itmd4515.mbudhale;

import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;

public abstract class BaseValidationTest {

    protected static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;
    protected static Validator validator;

    @BeforeAll
    public static void BeforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void BeforeEach() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        Account a = new Account(AccountType.VENDOR, "Maxwell Street Market", "Maxwell@gmail.com", "773-769-1111", "5039 N Broadway, Chicago Illinois 60640", LocalDate.now());
        tx.begin();
        em.persist(a);
        tx.commit();
    }

    @AfterEach
    public void AfterEach() {
        Account a = em.
                createQuery("select a "
                        + "from Account a "
                        + "where a.company_Name='Maxwell Street Market'", Account.class)
                .getSingleResult();
        tx.begin();
        em.remove(a);
        tx.commit();
        em.close();
    }

}
