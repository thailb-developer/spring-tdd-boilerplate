package com.thailb.testcontainer.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "threads")
@Getter
@Setter
public class Thread {
    @Id
    private String id;

    private String content;
    private Date postedDate;

    public Thread(String content) {
        this.content = content;
        this.postedDate = Date.from(Instant.now());
    }

    public static Thread from(CreateThreadDTO createThreadDTO) {
        return new Thread(createThreadDTO.content());
    }
}
