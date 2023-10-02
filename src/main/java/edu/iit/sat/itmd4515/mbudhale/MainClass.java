package edu.iit.sat.itmd4515.mbudhale;

import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class MainClass {

    public static void main(String[] args) {
        Account a = new Account(AccountType.VENDOR, "Maxwell Street Market", "Maxwell@gmail.com", "773-769-1111", "5039 N Broadway, Chicago Illinois 60640", LocalDate.now());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        System.out.println(em.getProperties());
        System.out.println("Before" + a.toString());
        tx.begin();
        em.persist(a);
        tx.commit();
        System.out.println("After" + a.toString());
        em.close();

    }

}
