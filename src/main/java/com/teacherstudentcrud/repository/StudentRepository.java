package com.teacherstudentcrud.repository;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByFirstNameIgnoreCase(String firstName);
    List<Student> findAllByLastNameIgnoreCase(String lastName);
    List<Student> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(String lastName, String firstName);


}
