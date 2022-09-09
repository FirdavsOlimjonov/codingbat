package ai.ecma.codingbat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication
@EnableScheduling
public class AppCodingbatApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppCodingbatApplication.class, args);
    }

}
