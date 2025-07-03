package com.Usman.SpringSecurity.controller;

import com.Usman.SpringSecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class studentController {

    private List<Student> students  = new ArrayList<>(List.of(
            new Student(1,"usman", 57),
            new Student(2,"eletu", 67),
            new Student(3,"magic", 58)
    ));

    @GetMapping("/students")
    public List<Student> getStudent(){
        return students;
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

    @GetMapping("/csrf_token")
    public CsrfToken csrfToken (HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
