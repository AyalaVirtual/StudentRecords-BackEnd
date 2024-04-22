package org.example.service;

import org.example.exception.InformationNotFoundException;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    private StudentRepository studentRepository;


    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    /**\
     * This is a GET request that checks to see if the list of students is empty before either throwing an InformationNotFoundException, or returning the list of students.
     *
     * @return a list of all students
     */
    public List<Student> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();

        if (studentList.isEmpty()) {
            throw new InformationNotFoundException("student list not found");
        } else {
            return studentList;
        }
    }




}
