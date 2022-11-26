package com.teacherstudentcrud.repository;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import org.springframework.data.convert.ThreeTenBackPortConverters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findFirstById(Long id);

    @Query("SELECT t FROM Teacher t ORDER BY t.lastName, t.firstName ASC")
    List<Teacher> findAllOrderByLastName();

    List<Teacher> findAllByFirstNameIgnoreCase(String firstName);

    List<Teacher> findAllByLastNameIgnoreCase(String lastName);

    List<Teacher> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(String lastName, String firstName);

    @Query(value = "SELECT DISTINCT * FROM teachers AS t LEFT JOIN students_teachers AS st ON t.id = st.teacher_id WHERE st.student_id = :id", nativeQuery = true)
    List<Teacher> findAllByStudentId(@Param("id") Long id);

}
