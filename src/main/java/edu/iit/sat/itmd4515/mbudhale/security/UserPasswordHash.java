
package edu.iit.sat.itmd4515.mbudhale.security;

import jakarta.inject.Inject;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

public class UserPasswordHash {
    
    @Inject private Pbkdf2PasswordHash hash;
    
    @PrePersist
    @PreUpdate
    private void passwordHash(User u)
    {
        u.setPassword(hash.generate(u.getPassword().toCharArray()));
    }
    
}
