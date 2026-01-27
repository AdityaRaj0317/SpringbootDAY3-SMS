package org.example.day3sms.service;

import org.example.day3sms.DTO.StudentRequestDTO;
import org.example.day3sms.DTO.StudentResponseDTO;
import org.example.day3sms.exception.StudentNotFoundException;
import org.example.day3sms.model.StudentModel;
import org.example.day3sms.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

//    public StudentModel addStudent(StudentModel student){
//        return repository.save(student);
//    }

    public StudentResponseDTO addStudent(StudentRequestDTO dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);

        return new StudentResponseDTO(saved.getId(), saved.getName(), saved.getAge(), saved.getEmail());
    }


    //Display Student
    @GetMapping("/students")

    public List<StudentResponseDTO> getAllStudents(){
        return repository.findAll()
                .stream()
                .map( s -> new StudentResponseDTO(s.getId(), s.getName(), s.getAge(), s.getEmail())).toList();
    }
    // public List<StudentModel> getStudents(){
//        return repository.findAll();
//    }

    //update
    public StudentModel updateStudents(String id , StudentModel student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(()->new RuntimeException("No Student Found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        return repository.save(existingStudent);
    }

    //Delete
    public StudentModel deleteStudents(String id , StudentModel student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("No Student Found"));
        repository.delete(existingStudent);
        return existingStudent;
    }
}