package com.teacherstudentcrud.controller;

import com.teacherstudentcrud.model.Teacher;
import com.teacherstudentcrud.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    public final TeacherRepository repository;



    @ModelAttribute("teachers")
    public List<Teacher> allTeachers(){
        return repository.findAll();
    }
}
