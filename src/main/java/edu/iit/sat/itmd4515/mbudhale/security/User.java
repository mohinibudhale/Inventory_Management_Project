package edu.iit.sat.itmd4515.mbudhale.security;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SEC_USER")
@NamedQuery(name="User.findAll",query="select u from User u")
@EntityListeners(UserPasswordHash.class)
public class User{

    @Id
    @NotBlank(message = "Please enter a Username.")
    private String userName;
    
    @NotBlank(message = "Please enter a Password.")
    private String password;

    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "SEC_USER_GROUPS",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))

    private List<Group> groups = new ArrayList<>();
 
    public User() {
    }

    public User(String userName, String password, Boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group g) {
        if (g != null && !this.groups.contains(g)) {
            this.groups.add(g);
            g.getUsers().add(this);
        }
    }

    public void removeGroup(Group g) {
        if (g != null && this.groups.contains(g)) {
            this.groups.remove(g);
            g.getUsers().remove(this);
        }
    }

}
