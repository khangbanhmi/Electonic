package main.java.com.capstone.electronic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing

public class ElectronicApplication {
    public static void main(Stirng[] args){
        SpringApplication.run(ElectronicApplication.class, args);
    }
}
