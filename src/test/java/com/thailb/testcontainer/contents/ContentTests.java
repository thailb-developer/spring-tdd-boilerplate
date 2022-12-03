package com.thailb.testcontainer.contents;

import com.thailb.testcontainer.DemoTestApplication;
import com.thailb.testcontainer.models.entities.CreateThreadDTO;
import com.thailb.testcontainer.repositories.ThreadRepository;
import com.thailb.testcontainer.services.ContentService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
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
public class ContentTests {

    private final ThreadRepository threadRepository;
    private final ContentService systemUnderTest;

    @Autowired
    public ContentTests(ThreadRepository threadRepository, ContentService contentService) {
        this.threadRepository = threadRepository;
        this.systemUnderTest = contentService;
    }

    @Container
    private static final MongoDBContainer mongoDB = new MongoDBContainer(
            DockerImageName.parse("mongo:6-focal"))
            .withExposedPorts(27017)
            .waitingFor(Wait.forHttp("/"));

    @DynamicPropertySource
    static void setConnectionProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDB::getReplicaSetUrl);
    }

    @AfterAll
    public static void tearDown() {
        mongoDB.stop();
    }

    @DisplayName("Test Testcontainer instance is up and running.")
    @Test
    void startTestContainer_CorrectTestContainerConfigure_Successful() {
        assertTrue(mongoDB.isRunning());
    }

    @DisplayName("Test insert new threads to blank database.")
    @Test
    void createThread_ValidInput_Successful() {
        CreateThreadDTO threadInput = new CreateThreadDTO("Test Thread");
        systemUnderTest.createThread(threadInput);

        assertTrue(threadRepository.countAllDocuments() > 0);
    }
}
