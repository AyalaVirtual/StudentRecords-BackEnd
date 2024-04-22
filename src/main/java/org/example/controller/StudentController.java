package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/") // http://localhost:9092/api/
public class StudentController {
    private StudentService studentService;


    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }


    /**
     * This sets the path for GET requests for all students and checks if the list of students is empty or not before deciding whether to send an HTTP status message of NOT FOUND or OK
     *
     * @return the HTTP status message
     */
    @GetMapping(path = "/students/")
    public ResponseEntity<?> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();

        if (studentList.isEmpty()) {
            message.put("message", "cannot find any students");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "success");
            message.put("data", studentList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }




}
