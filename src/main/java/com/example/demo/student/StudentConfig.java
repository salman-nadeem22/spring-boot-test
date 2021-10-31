package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            var salman = new Student("salman", "salman@gmail.com", 21, LocalDate.of(2000, Month.AUGUST, 25));
            var nadeem = new Student("nadeem", "nadeem@gmail.com", 41, LocalDate.of(1980, Month.AUGUST, 25));
            studentRepository.saveAll(List.of(salman, nadeem));
        };
    };
}
