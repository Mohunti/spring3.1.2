package com.example.springboot_rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @Column(name = "roles",unique = true)
    private String role;

    @JsonIgnore
   @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
   private Set<User> users;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

    @Override
    public String toString() {
        return role;
    }
}
