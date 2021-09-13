package com.example.springboot_rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "age")
    private int age;


    @Column(name = "surName")
    private String surName;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @Column(name = "username")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2,max = 120,message = "Name should be between 2 and 8 characters")
    private String username;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_ID"),
            inverseJoinColumns = @JoinColumn(name = "role_ID"))

 private Set<Role> roles;


    public User(int age, String surName, String password, String username) {
        this.age = age;
        this.surName = surName;
        this.password = password;
        this.username = username;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", surName='" + surName + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
