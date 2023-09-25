package edu.iit.sat.itmd4515.mbudhale;

import edu.iit.sat.itmd4515.mbudhale.domain.AccountType;
import edu.iit.sat.itmd4515.mbudhale.domain.Accounts;
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
        List<Accounts> resultList = em.createQuery("select a from Accounts a where a.company_Name='Maxwell Street Market'", Accounts.class)
                .getResultList();
        assertTrue(!resultList.isEmpty());
        for (Accounts a : resultList) {
            Set<ConstraintViolation<Accounts>> violations = validator.validate(a);
            assertTrue(violations.isEmpty());
        }
    }

    @Test
    public void testInvalidEntity() {
        //Purposly Passing wrong input to check if validation constraint works 
        Accounts a = new Accounts(AccountType.VENDOR, " ", "bademailGmail", "11112222", null, null);
        Set<ConstraintViolation<Accounts>> violations = validator.validate(a);
        assertFalse(violations.isEmpty());
        assertEquals(5, violations.size());
    }

}
