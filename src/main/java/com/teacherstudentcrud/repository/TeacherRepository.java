package com.teacherstudentcrud.repository;

import com.teacherstudentcrud.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}
