package ru.mattgroy.conspectusshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {ConspectusShareApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class ConspectusShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConspectusShareApplication.class, args);
    }

}
