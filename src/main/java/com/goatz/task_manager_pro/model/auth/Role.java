package com.goatz.task_manager_pro.model.auth;

import com.goatz.task_manager_pro.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
/**
 * JPA entity representing a user role in the system.
 * Manages relationships to users and capabilities.
 */
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unique name of the role. */
    @Column(unique = true, nullable = false)
    private String name;

    /** Users assigned to this role. */
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    /** Capabilities associated with this role. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_capabilities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "capability_id")
    )
    private Set<Capability> capabilities = new HashSet<>();

    /**
     * Adds a capability to this role and updates the capability's roles set.
     * @param capability Capability to add
     */
    public void addCapability(@NotNull Capability capability) {
        this.capabilities.add(capability);
        capability.getRoles().add(this);
    }

    /**
     * Removes a capability from this role and updates the capability's roles set.
     * @param capability Capability to remove
     */
    public void removeCapability(@NotNull Capability capability) {
        this.capabilities.remove(capability);
        capability.getRoles().remove(this);
    }

    /**
     * Adds a user to this role and sets the user's role reference.
     * @param user User to add
     */
    public void addUser(User user) {
        this.users.add(user);
        user.setRole(this);
    }

    /**
     * Removes a user from this role and clears the user's role reference.
     * @param user User to remove
     */
    public void removeUser(User user) {
        this.users.remove(user);
        user.setRole(null);
    }

    /**
     * Bulk adds users to this role, maintaining relationship consistency.
     * @param users Collection of users to add
     */
    public void addUsers(Collection<User> users) {
        users.forEach(this::addUser);
    }
}
