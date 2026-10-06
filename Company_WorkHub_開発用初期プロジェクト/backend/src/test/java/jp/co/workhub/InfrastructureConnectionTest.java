package jp.co.workhub;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Tag("connection")
@SpringBootTest
class InfrastructureConnectionTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    void postgresRespondsWithoutBusinessTables() throws Exception {
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT 1")) {
            assertThat(result.next()).isTrue();
            assertThat(result.getInt(1)).isEqualTo(1);
        }
    }

    @Test
    void redisRespondsWithoutWritingData() {
        try (RedisConnection connection = redisConnectionFactory.getConnection()) {
            assertThat(connection.ping()).isEqualTo("PONG");
        }
    }
}
