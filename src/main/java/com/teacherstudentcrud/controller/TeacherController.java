package com.teacherstudentcrud.controller;

import com.teacherstudentcrud.model.Student;
import com.teacherstudentcrud.model.Teacher;
import com.teacherstudentcrud.repository.StudentRepository;
import com.teacherstudentcrud.repository.TeacherRepository;
import com.teacherstudentcrud.service.SearchService;
import com.teacherstudentcrud.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;
    private final SearchService searchService;
    static final int SIZE = 5;

    @GetMapping(value = "")
    public String teachersList(@RequestParam(required = false, defaultValue = "0") int currentPage,
                               @RequestParam(required = false) String direction,
                               @RequestParam(required = false, defaultValue = "id") String sort,
                               Model model, HttpSession session) {
        if (direction != null && direction.equals("forward")) {
            currentPage++;
            if (currentPage > teacherRepository.findAll().size() / SIZE) {
                currentPage--;
            }
        } else if (direction != null && direction.equals("backward")) {
            currentPage--;
            if (currentPage < 0) {
                currentPage = 0;
            }
        }
        if (sort.equals("id") && session.getAttribute("sort") == null) {
            session.setAttribute("sort", sort);
        } else if (!sort.equals("id")) {
            session.setAttribute("sort", sort);
        }
        Pageable firstPageWithTwoElements = PageRequest.of(currentPage, SIZE, Sort.by((String) session.getAttribute("sort")));
        model.addAttribute("teacherPagin", teacherRepository.findAll(firstPageWithTwoElements).getContent());
        model.addAttribute("currentPage", currentPage);

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
        teacherService.addTeacherToStudents(teacher);
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
        teacherService.removeTeacherFromStudent(teacher.getId());
        teacherService.addTeacherToStudents(teacher);
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }

    @GetMapping(value = "/{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        teacherService.removeTeacherFromStudent(id);
        teacherRepository.deleteById(id);
        return "redirect:/teacher";
    }

    @GetMapping(value = "/search")
    public String teacherSearch(@RequestParam String firstName, @RequestParam String lastName, Model model) {
        model.addAttribute("teacherSearchResult", searchService.teacherSearch(firstName, lastName));
        return "teacher/list";
    }

    @GetMapping(value = "/search/byStudent")
    public String teacherSearchByStudentName(@RequestParam String firstName, @RequestParam String lastName, Model model) {
        model.addAttribute("teacherSearchResult", searchService.teacherSearchByStudent(firstName, lastName));
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

