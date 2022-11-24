package com.teacherstudentcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class TeacherStudentCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherStudentCrudApplication.class, args);
    }


}
