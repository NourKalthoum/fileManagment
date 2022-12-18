package com.example.demo.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;
@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

   
    @ManyToOne(targetEntity = User.class)
    @JsonBackReference
    private User gowner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_file", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    @JsonBackReference
    private List<File> files;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_users", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonBackReference
    private List<User> users;
}
