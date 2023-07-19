package eureka.resourceservice.config;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgresExtension implements BeforeAllCallback, AfterAllCallback {

    private PostgreSQLContainer<?> postgreSQLContainer;

    @Override
    public void afterAll(ExtensionContext context) throws Exception {

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.9-alpine");
        postgreSQLContainer.start();

        System.setProperty("spring.test.database.replace", "none");
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
    }
}
