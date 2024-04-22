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
        student1.setName("Jon Snow");
        student1.setEmail("princeWhoWasPromised@winterfell.com");
        student1.setDateOfBirth("08/14/1988");
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("Daenerys Targaryen");
        student2.setEmail("motherOfDragons@dragonstone.com");
        student2.setDateOfBirth("06/30/1994");
        studentRepository.save(student2);

        Student student3 = new Student();
        student3.setName("Jaime Lannister");
        student3.setEmail("kingslayer@casterlyrock.com");
        student3.setDateOfBirth("07/02/1977");
        studentRepository.save(student3);

        Student student4 = new Student();
        student4.setName("Arya Stark");
        student4.setEmail("noOne@facelessMen.com");
        student4.setDateOfBirth("03/01/2008");
        studentRepository.save(student4);

        Student student5 = new Student();
        student5.setName("Olenna Tyrell");
        student5.setEmail("queenOfThorns@highgarden.com");
        student5.setDateOfBirth("05/12/1972");
        studentRepository.save(student5);

    }
}
