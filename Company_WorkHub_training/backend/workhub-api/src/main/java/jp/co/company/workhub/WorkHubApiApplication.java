package jp.co.company.workhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class WorkHubApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkHubApiApplication.class, args);
    }
}
