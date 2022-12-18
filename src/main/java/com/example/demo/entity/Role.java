package com.example.demo.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor
//@Table(name = "role")
public class Role {
   
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  public Role(Integer id, ERole name) {
    this.id = id;
    this.name = name;
}
}
