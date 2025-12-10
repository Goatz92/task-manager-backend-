package com.goatz.task_manager_pro.model;

import com.goatz.task_manager_pro.model.auth.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * JPA entity representing a user in the system.
 * Implements Spring Security's UserDetails for authentication and authorization.
 * Inherits auditing fields from AbstractEntity.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    /** Role assigned to the user. */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * Returns the authorities granted to the user (role and capabilities).
     * Used by Spring Security for access control.
     * @return Collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        role.getCapabilities().forEach(capability ->
            grantedAuthorities.add(new SimpleGrantedAuthority(capability.getName()))
        );
        return grantedAuthorities;
    }
}