package org.example.studentrecordsapi.repository.repository;

import org.example.studentrecordsapi.model.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // This method finds a student by  its id
    Optional<Student> findById(Long studentId);

    // This method finds a student by their name
    Student findByName(String studentName);

    // This method finds a student by their email address
    Student findByEmail(String studentEmail);
}
