package com.jgiga.SpringSecurity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgiga.SpringSecurity.models.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>(
            List.of(new Student(1, "papu", 60),
                    new Student(2, "nashe", 20),
                    new Student(3, "sergio", 80)));

    @GetMapping("")
    public List<Student> getAll() {
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request) {

        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("")
    public List<Student> createOne(@RequestBody Student newStudent) {
        students.add(newStudent);
        return students;
    }

}
