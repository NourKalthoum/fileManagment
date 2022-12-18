package com.example.demo.entity;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean free;

   
    @ManyToOne(targetEntity = User.class)
    @JsonBackReference
    private User fowner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_file", joinColumns = @JoinColumn(name = "file_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonBackReference
    private List<Group> groups;

    @JsonIgnoreProperties(value = {"file"},allowSetters = true)
    @OneToMany(mappedBy = "file")
    @JsonBackReference
    private List<Reservation> reservations;
}
