package com.example.demo.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor

@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mobile;
    private Boolean status;
    private Boolean isActive;
    private String zipCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnoreProperties(value = {"user"},allowSetters = true)
    @OneToMany(mappedBy = "fowner")
    private List<File> files;

    @JsonIgnoreProperties(value = {"user"},allowSetters = true)
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;

    @JsonIgnoreProperties(value = {"user"},allowSetters = true)
    @OneToMany(mappedBy = "gowner")
    private List<Group> group;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, Set<Role> roles, Boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status = status;

    }

    public User(String username, String password, Boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;

    }

    public User(String username, String password,String firstName, String lastName) {
        
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

    }


    public User(Long id, String firstName, String lastName, String username, String password, String mobile, Boolean status, Boolean isActive, String zipCode, Set<Role> roles, List<File> files, List<Reservation> reservations, List<Group> groups, List<Group> group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.status = status;
        this.isActive = isActive;
        this.zipCode = zipCode;
        this.roles = roles;
        this.files = files;
        this.reservations = reservations;
        this.groups = groups;
        this.group = group;
    }
  

   
   
}
