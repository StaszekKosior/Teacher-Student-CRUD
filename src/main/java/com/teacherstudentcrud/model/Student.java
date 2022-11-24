package com.teacherstudentcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2)
    private String firstName;

    private String lastName;

    @Min(18)
    private int age;

    @Email
    private String email;

    private String fieldOfStudy;

    @ManyToMany
    private List<Teacher> teachers = new ArrayList<>();

}
