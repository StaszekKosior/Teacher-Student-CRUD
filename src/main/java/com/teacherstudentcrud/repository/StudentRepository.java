package com.teacherstudentcrud.repository;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s ORDER BY s.lastName, s.firstName ASC")
    List<Student> findAllOrderByLastName();

    List<Student> findAllByFirstNameIgnoreCase(String firstName);

    List<Student> findAllByLastNameIgnoreCase(String lastName);

    List<Student> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(String lastName, String firstName);

    @Query(value = "SELECT DISTINCT * FROM students AS s LEFT JOIN students_teachers AS st ON s.id = st.student_id WHERE st.teacher_id = :id", nativeQuery = true)
    List<Student> findAllByTeacherId(@Param("id") Long id);

}


