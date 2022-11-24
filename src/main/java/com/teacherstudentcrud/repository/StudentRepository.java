package com.teacherstudentcrud.repository;

import com.teacherstudentcrud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {


}
