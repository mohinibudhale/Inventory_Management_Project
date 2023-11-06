
package edu.iit.sat.itmd4515.mbudhale.domain;

public enum AccountType {
    CUSTOMER("customer"),
    VENDOR("vendor"),
    EMPLOYEE("employee");
    
    private String label;
    private AccountType(String label)
    {
        this.label=label;
    }   

    public String getLabel() {
        return label;
    }
    
}
