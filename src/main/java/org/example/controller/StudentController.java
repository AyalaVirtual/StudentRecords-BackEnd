package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;


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


    /**
     * This sets the path for GET requests for an individual student and checks if the student exists or not before deciding whether to send an HTTP status message of OK or NOT FOUND
     *
     * @param studentId represents the id of the specific student the user is trying to get
     * @return the HTTP status message
     */
    @GetMapping(path = "/students/{studentId}/")
    public ResponseEntity<?> getStudentById(@PathVariable(value = "studentId") Long studentId) {
        Optional<Student> studentOptional = studentService.getStudentById(studentId);

        if (studentOptional.isPresent()) {
            message.put("message", "success");
            message.put("data", studentOptional.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "student with id " + studentId + " not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }




}
