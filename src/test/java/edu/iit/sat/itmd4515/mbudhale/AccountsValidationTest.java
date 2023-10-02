package edu.iit.sat.itmd4515.mbudhale;

import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.domain.Account;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountsValidationTest extends BaseValidationTest {

    @Test
    public void IsValidEntity() {
        List<Account> resultList = em.createQuery("select a from Account a where a.company_Name='Maxwell Street Market'", Account.class)
                .getResultList();
        assertTrue(!resultList.isEmpty());
        for (Account a : resultList) {
            Set<ConstraintViolation<Account>> violations = validator.validate(a);
            assertTrue(violations.isEmpty());
        }
    }

    @Test
    public void testInvalidEntity() {
        //Purposly Passing wrong input to check if validation constraint works 
        Account a = new Account(AccountType.VENDOR, " ", "bademailGmail", "11112222", null, null);
        Set<ConstraintViolation<Account>> violations = validator.validate(a);
        assertFalse(violations.isEmpty());
        assertEquals(5, violations.size());
    }

}
