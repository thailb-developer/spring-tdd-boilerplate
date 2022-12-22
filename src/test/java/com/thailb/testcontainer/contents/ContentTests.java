package com.thailb.testcontainer.contents;

import com.thailb.testcontainer.DemoTestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static com.mongodb.assertions.Assertions.assertTrue;

@SpringBootTest(
        classes = DemoTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Slf4j
public class ContentTests {
    @Container
    private static final MongoDBContainer mongoDB = new MongoDBContainer(
            DockerImageName.parse("mongo:6-focal"))
            .withExposedPorts(27017)
            .waitingFor(Wait.forHttp("/"));

    @SuppressWarnings("resource")
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:13-alpine")
                .withExposedPorts(5432);

    @DynamicPropertySource
    static void setConnectionProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDB::getReplicaSetUrl);
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @BeforeAll
    public static void setUp() {
        mongoDB.start();
        postgreSQLContainer.start();
    }

    @AfterAll
    public static void tearDown() {
        mongoDB.stop();
        postgreSQLContainer.stop();
    }

    @DisplayName("Test MongoDB instance is up and running.")
    @Test
    void startTestContainer_MongoDB_Successful() {
        assertTrue(mongoDB.isRunning());
    }

    @DisplayName("Test Postgresql instance is up and running.")
    @Test
    void startTestContainer_Postgresql_Successful() {
        assertTrue(postgreSQLContainer.isRunning());
    }
}
