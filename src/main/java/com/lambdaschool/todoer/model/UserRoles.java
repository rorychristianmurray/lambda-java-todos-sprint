package com.lambdaschool.todoer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "userroles")
public class UserRoles extends Auditable implements Serializable
{

    // userid foreign key to user, joining them together in a Many UserRoles to one User relationship
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"userroles", "hibernateLazyInitialized"})
    @JoinColumn(name = "userid")
    private User user;

    // roleid foreign key to role
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
    private Role role;

    // generate base constructor
    public UserRoles()
    {
    }

    // generate regular constructor


    public UserRoles(User user, Role role)
    {
        this.user = user;
        this.role = role;
    }

    // generate getters and setter

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    // *** REVIEW THIS ***
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UserRoles))
        {
            return false;
        }
        UserRoles userRoles = (UserRoles) o;
        return getUser().equals(userRoles.getUser()) && getRole().equals(userRoles.getRole());
    }

    // *** REVIEW THIS ***
    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(), getRole());
    }
}