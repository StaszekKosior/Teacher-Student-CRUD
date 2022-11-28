package com.teacherstudentcrud.model;

import lombok.*;

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

    @Size(min = 2, message = "{firstName.format.error}")
    private String firstName;

    private String lastName;

    @Min(value = 18, message = "{age.format.error}")
    private int age;

    @Email
    private String email;

    private String fieldOfStudy;

    @ManyToMany
    @JoinTable(name = "students_teachers",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Teacher> teachers = new ArrayList<>();

    public String getName() {
        return firstName + " " + lastName;
    }
}
