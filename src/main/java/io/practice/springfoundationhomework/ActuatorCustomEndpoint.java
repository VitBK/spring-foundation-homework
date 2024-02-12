package io.practice.springfoundationhomework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Endpoint(id = "custom", enableByDefault = false)
public class ActuatorCustomEndpoint {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment env;

    @ReadOperation
    public CustomResponse getCustomInfo() {
        String dbUrl;
        try (Connection connection = dataSource.getConnection()) {
            dbUrl = connection.getMetaData().getURL();
        } catch (SQLException e) {
            dbUrl = e.getMessage();
        }
        String[] profiles = env.getActiveProfiles();
        return new CustomResponse(dbUrl, profiles);
    }

    public record CustomResponse(String dbUrl, String[] profiles) {
    }
}
