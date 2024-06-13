package com.spring.data.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringDataMongoDBApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringDataMongoDBApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
        return args -> {
            Address address = new Address(
                    "USA",
                    "Las Vegas",
                    "88901"
            );
            String email="alexsmith@gmail.com";
            Student student = new Student(
                    "Alex",
                    "Smith",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Computer Science", "Maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );
           // usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);
            repository.findStudentByEmail(email).ifPresentOrElse(s-> {
                System.out.println(s + " already exists");
            },()->{
                System.out.println("Inserting student " + student);
                repository.insert(student);
            });
        };
    }

    private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<Student> students = mongoTemplate.find(query, Student.class);
    }
}
