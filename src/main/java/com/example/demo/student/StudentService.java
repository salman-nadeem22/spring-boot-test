package com.example.demo.student;

import com.example.demo.student.requests.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService   {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("Email Already Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        if(!studentRepository.existsById(id)) {
            throw new IllegalStateException("Student Not Found.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Integer id, UpdateStudentRequest data) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student Not Found."));
        if(data.name != null && data.name.length() > 0 && data.name != student.getName()) {
            student.setName(data.name);
        }

        if(data.email != null && data.email.length() > 0 && data.name != student.getEmail()) {
            student.setEmail(data.email);
        }
    }
}
