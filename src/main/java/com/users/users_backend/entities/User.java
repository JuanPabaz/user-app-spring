package com.users.users_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "id_user"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_rol"
            ),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user","id_rol"})}
    )
    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>();
    }

    public User(String email, Long id, String lastName, String name, String password, List<Role> roles, String username) {
        this.email = email;
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
