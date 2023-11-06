
package edu.iit.sat.itmd4515.mbudhale.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SEC_GROUP")
@NamedQuery(name="Group.findAll",query="select g from Group g")
public class Group {

    @Id
    private String groupName;
    
    private String groupDescription;
    
    @ManyToMany(mappedBy = "groups")
    private List<User> users= new ArrayList<>();


    public Group() {
    }

    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupDescription() {
        return groupDescription;
    }
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    
}
