package org.example.seed;

import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.example.model.Student;


@Component
public class SeedData implements CommandLineRunner {
    private final StudentRepository studentRepository;


    @Autowired
    public SeedData(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setLastName("Smith");
        student1.setGrade(92);
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setFirstName("");
        student2.setLastName("");
        student2.setGrade(87);
        studentRepository.save(student2);

        Student student3 = new Student();
        student3.setFirstName("");
        student3.setLastName("");
        student3.setGrade(82);
        studentRepository.save(student3);

        Student student4 = new Student();
        student4.setFirstName("");
        student4.setLastName("");
        student4.setGrade(70);
        studentRepository.save(student4);

        Student student5 = new Student();
        student5.setFirstName("");
        student5.setLastName("");
        student5.setGrade(75);
        studentRepository.save(student5);

    }
}
