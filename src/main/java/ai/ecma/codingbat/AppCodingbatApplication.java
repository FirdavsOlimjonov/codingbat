package ai.ecma.codingbat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication
public class AppCodingbatApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppCodingbatApplication.class, args);
    }


//    @Bean
//    public HttpSecurity httpSecurity(){
//        return new HttpSecurity();
//    }

}
