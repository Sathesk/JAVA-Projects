package com.project_one.ems_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity  //JPA Annotation
@Table(name="employees")//creating in the name of employees
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//automatic value generation
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
}
