package com.teacherstudentcrud.controller;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import com.teacherstudentcrud.repository.StudentRepository;
import com.teacherstudentcrud.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    @GetMapping(value = "")
    public String teachersList() {

        return "teacher/list";
    }

    @GetMapping("/add")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/addForm";
    }

    @PostMapping("/add")
    public String addTeacher(@Valid @ModelAttribute Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "teacher/addForm";
        }
        List<Student> studentsList = teacher.getStudents();
        if (studentsList.isEmpty()) {
        } else {
            for (Student student : studentsList) {
                studentRepository.findById(student.getId()).get().getTeachers().add(teacher);
            }
        }
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }

    @GetMapping(value = "/update")
    public String updateTeacher(@RequestParam("id") Long id, Model model) {
        model.addAttribute("teacher", teacherRepository.findById(id));
        return "teacher/addForm";
    }

    @PostMapping(value = "/update")
    public String updateTeacher(@Valid @ModelAttribute Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "teacher/addForm";
        }
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }

    @GetMapping(value = "/{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        Teacher teacher = teacherRepository.findFirstById(id);
        List<Student> studentsList = studentRepository.findAll();
        for (Student student : studentsList) {
            if (student.getTeachers().contains(teacher)){
                student.getTeachers().remove(teacher);
            }
        }
        teacherRepository.deleteById(id);
        return "redirect:/teacher";
    }

    @GetMapping(value = "/search")
    public String teacherSearch(@RequestParam String firstName, @RequestParam String lastName, Model model){
        List <Teacher> searchResult = new ArrayList<>();
        if (!lastName.equals("") && !firstName.equals("")){
            searchResult = teacherRepository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName.trim().toLowerCase(),
                    lastName.trim().toLowerCase());
        } else if (!lastName.equals("") && firstName.equals("")) {
            searchResult = teacherRepository.findAllByLastNameIgnoreCase(lastName.trim().toLowerCase());
        } else if (lastName.equals("") && !firstName.equals("")){
            searchResult = teacherRepository.findAllByFirstNameIgnoreCase(firstName.trim().toLowerCase());
        }
        model.addAttribute("teacherSearchResult", searchResult);
        return "teacher/list";
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

