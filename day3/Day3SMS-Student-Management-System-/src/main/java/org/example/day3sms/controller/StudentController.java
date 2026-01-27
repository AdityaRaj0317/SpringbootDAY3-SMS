package org.example.day3sms.controller;


import org.example.day3sms.DTO.StudentRequestDTO;
import org.example.day3sms.DTO.StudentResponseDTO;
import org.example.day3sms.model.StudentModel;
import org.example.day3sms.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/")
    public String l(){return "hi there.";}
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/add-student")
    public StudentResponseDTO addStart(@RequestBody StudentRequestDTO student){
        return service.addStudent(student);
    }


    //Display Student
    @GetMapping("/students")
    public List<StudentResponseDTO> getStudents(){
        return service.getAllStudents();
    }

    //update Student
    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel student){
        return service.updateStudents(id,student);
    }

    //Delete Student
    @DeleteMapping("/delete/{id}")
    public StudentModel deleteStudent(@PathVariable String id, @RequestBody StudentModel student){
        return service.deleteStudents(id,student);
    }
}
