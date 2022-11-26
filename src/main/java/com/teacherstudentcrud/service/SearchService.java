package com.teacherstudentcrud.service;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import com.teacherstudentcrud.repository.StudentRepository;
import com.teacherstudentcrud.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public SearchService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> teacherSearch(String firstName, String lastName){
        List<Teacher> searchResult = new ArrayList<>();
        if (!lastName.equals("") && !firstName.equals("")) {
            searchResult = teacherRepository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName.trim().toLowerCase(),
                    lastName.trim().toLowerCase());
        } else if (!lastName.equals("") && firstName.equals("")) {
            searchResult = teacherRepository.findAllByLastNameIgnoreCase(lastName.trim().toLowerCase());
        } else if (lastName.equals("") && !firstName.equals("")) {
            searchResult = teacherRepository.findAllByFirstNameIgnoreCase(firstName.trim().toLowerCase());
        }
        return searchResult;
    }

    public List<Teacher> teacherSearchByStudent(String firstName, String lastName){
        List<Teacher> searchResult = new ArrayList<>();
        List<Student> students = studentSearch(firstName, lastName);
        for (Student student : students) {
            searchResult.addAll(teacherRepository.findAllByStudentId(student.getId()));
        }
        return searchResult;
    }
    public List<Student> studentSearch(String firstName, String lastName){
        List <Student> searchResult = new ArrayList<>();
        if (!lastName.equals("") && !firstName.equals("")){
            searchResult = studentRepository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName.trim().toLowerCase(),
                    lastName.trim().toLowerCase());
        } else if (!lastName.equals("") && firstName.equals("")) {
            searchResult = studentRepository.findAllByLastNameIgnoreCase(lastName.trim().toLowerCase());
        } else if (lastName.equals("") && !firstName.equals("")){
            searchResult = studentRepository.findAllByFirstNameIgnoreCase(firstName.trim().toLowerCase());
        }
        return searchResult;
    }
    public List<Student> studentSearchByTeacher(String firstName, String lastName){
        List<Student> searchResult = new ArrayList<>();
        List<Teacher> teachers = teacherSearch(firstName, lastName);
        for (Teacher teacher : teachers) {
            searchResult.addAll(studentRepository.findAllByTeacherId(teacher.getId()));
        }
        return searchResult;
    }










}
