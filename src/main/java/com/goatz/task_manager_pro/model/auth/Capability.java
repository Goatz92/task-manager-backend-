package com.goatz.task_manager_pro.model.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * JPA entity representing a capability (permission) in the system.
 * Manages relationships to roles.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "capabilities")
public class Capability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unique name of the capability. */
    @Column(unique = true, nullable = false)
    private String name;

    /** Description of the capability. */
    private String description;

    /** Roles associated with this capability. */
    @ManyToMany(mappedBy = "capabilities", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    /**
     * Adds a role to this capability and updates the role's capabilities set.
     * @param role Role to add
     */
    public void addRole(Role role) {
        this.roles.add(role);
        role.getCapabilities().add(this);
    }

    /**
     * Removes a role from this capability and updates the role's capabilities set.
     * @param role Role to remove
     */
    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getCapabilities().remove(this);
    }
}
