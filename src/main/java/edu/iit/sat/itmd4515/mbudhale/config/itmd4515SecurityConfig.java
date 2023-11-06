package edu.iit.sat.itmd4515.mbudhale.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@Named
@ApplicationScoped
@DeclareRoles({"ADMIN_ROLE", "EMPLOYEE_ROLE", "CUSTOMER_ROLE"})
@DatabaseIdentityStoreDefinition(dataSourceLookup = "java:app/jdbc/itmd4515DS",
        callerQuery = "SELECT PASSWORD FROM SEC_USER WHERE USERNAME = ?",
        groupsQuery = "SELECT GROUPNAME FROM SEC_USER_GROUPS WHERE USERNAME = ?")
@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(
        loginPage = "/login.xhtml",
        errorPage = "/error.xhtml"))
public class itmd4515SecurityConfig {

}
