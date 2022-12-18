package com.example.demo.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor
//@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Date createAt;

    @JsonIgnoreProperties(value = {"reservation"},allowSetters = true)
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JsonIgnoreProperties(value = {"reservation"},allowSetters = true)
    @ManyToOne(targetEntity = File.class)
    private File file;


}
