package org.example.day3sms.repository;


import org.example.day3sms.model.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository <StudentModel, String> {

}
