package org.example.service;

import org.example.exception.InformationExistException;
import org.example.exception.InformationNotFoundException;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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


    /**
     * This is a GET request that checks to see if an individual student exists before either returning it, or throwing an InformationNotFoundException.
     *
     * @param studentId represents the id of the specific student the user is trying to get
     * @return student by id if they exist
     */
    public Optional<Student> getStudentById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            return studentOptional;
        } else {
            throw new InformationNotFoundException("student with student id " + studentId + " not found");
        }
    }


    /**
     * This is a POST request that checks to see if the student that the user is trying to create already exists before either throwing an InformationExistException, or saving the newly created student to the repository.
     *
     * @param studentObject represents the new student object the user is trying to create
     * @return the newly created student
     */
    public Student createStudent(Student studentObject) {
        Student student = studentRepository.findByFullName(studentObject.getFirstName(), studentObject.getLastName());

        if (student != null) {
            throw new InformationExistException("student with name " + studentObject.getFullName() + " already exists");
        } else {
            return studentRepository.save(student);
        }
    }




}
