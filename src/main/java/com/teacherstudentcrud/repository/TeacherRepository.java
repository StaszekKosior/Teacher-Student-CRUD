package com.teacherstudentcrud.repository;

import com.teacherstudentcrud.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findFirstById(Long id);

    List<Teacher> findAllByFirstNameIgnoreCase(String firstName);

    List<Teacher> findAllByLastNameIgnoreCase(String lastName);

    List<Teacher> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(String lastName, String firstName);

    @Query(value = "SELECT DISTINCT * FROM teachers AS t LEFT JOIN students_teachers AS st ON t.id = st.teacher_id WHERE st.student_id = :id", nativeQuery = true)
    List<Teacher> findAllByStudentId(@Param("id") Long id);

}
