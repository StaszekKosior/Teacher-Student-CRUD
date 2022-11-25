package com.teacherstudentcrud.controller;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import com.teacherstudentcrud.repository.StudentRepository;
import com.teacherstudentcrud.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    @GetMapping(value = "")
    public String studentsList() {

        return "student/list";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/addForm";
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student/addForm";
        }
        studentRepository.save(student);
        return "redirect:/student";
    }

    @GetMapping(value = "/update")
    public String updateStudent(@RequestParam("id") Long id, Model model) {
        model.addAttribute("student", studentRepository.findById(id));
        return "student/addForm";
    }

    @PostMapping(value = "/update")
    public String updateStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student/addForm";
        }
        studentRepository.save(student);
        return "redirect:/student";
    }

    @GetMapping(value = "/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
        return "redirect:/student";
    }

    @GetMapping(value = "/search")
    public String studentSearch(@RequestParam String firstName, @RequestParam String lastName, Model model){
        List <Student> searchResult = new ArrayList<>();
        if (!lastName.equals("") && !firstName.equals("")){
            searchResult = studentRepository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName.trim().toLowerCase(),
                    lastName.trim().toLowerCase());
        } else if (!lastName.equals("") && firstName.equals("")) {
            searchResult = studentRepository.findAllByLastNameIgnoreCase(lastName.trim().toLowerCase());
        } else if (lastName.equals("") && !firstName.equals("")){
            searchResult = studentRepository.findAllByFirstNameIgnoreCase(firstName.trim().toLowerCase());
        }
        model.addAttribute("studentSearchResult", searchResult);
        return "student/list";
    }


    @ModelAttribute("students")
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    @ModelAttribute("teachers")
    public List<Teacher> allTeachers() {
        return teacherRepository.findAll();
    }
}
