package jp.co.company.workhub;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@Tag("connection")
@SpringBootTest
@ActiveProfiles("local")
class InfrastructureConnectionTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    void connectsToPostgreSqlAndRedis() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertThat(result).isEqualTo(1);

        String response;
        try (var connection = redisConnectionFactory.getConnection()) {
            response = connection.ping();
        }
        assertThat(response).isEqualTo("PONG");
    }
}
