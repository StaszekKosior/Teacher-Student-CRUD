package com.teacherstudentcrud.service;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import com.teacherstudentcrud.repository.StudentRepository;
import com.teacherstudentcrud.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherService {
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    public TeacherService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }
    public void addTeacherToStudents(Teacher teacher){
        List<Student> studentsList = teacher.getStudents();
        if (studentsList.isEmpty()) {
        } else {
            for (Student student : studentsList) {
                studentRepository.findById(student.getId()).get().getTeachers().add(teacher);
            }
        }
    }
    public void removeTeacherFromStudent(Long id){
        Teacher teacher = teacherRepository.findFirstById(id);
        List<Student> studentsList = studentRepository.findAll();
        for (Student student : studentsList) {
            if (student.getTeachers().contains(teacher)) {
                student.getTeachers().remove(teacher);
            }
        }
    }


}
