package com.example.demo.student.requests;

public class UpdateStudentRequest {
    public String name;
    public String email;

    public UpdateStudentRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
