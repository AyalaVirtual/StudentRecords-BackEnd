package org.example.repository;

import org.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // This method finds a fizzbuzz by  its id
    Optional<Student> findById(Long studentId);

    // This method finds a student by their full name
    Student findByFullName(String studentFirstName, String studentLastName);

    // This method finds a student by their last name
    Student findByLastName(String studentLastName);
}
