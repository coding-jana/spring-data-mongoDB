package com.spring.data.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringDataMongoDBApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringDataMongoDBApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository repository){
        return args -> {
            Address address = new Address(
                    "USA",
                    "Las Vegas",
                    "88901"
            );
            Student student = new Student(
                    "Alex",
                    "Smith",
                    "alexsmith@gmail.com",
                    Gender.MALE,
                    address,
                    List.of("Computer Science", "Maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );
            repository.insert(student);
        };
    }
}
