package com.teacherstudentcrud.repository;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

Teacher findFirstById(Long id);


}
