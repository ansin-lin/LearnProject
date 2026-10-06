package jp.co.workhub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class WorkHubApplicationTest {
    @Test
    void applicationContextLoads() {
        // Only a framework smoke test, not authentication or business acceptance.
    }
}
