package jp.co.company.workhub.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "jp.co.company.workhub")
public class WorkHubBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkHubBatchApplication.class, args);
    }
}
