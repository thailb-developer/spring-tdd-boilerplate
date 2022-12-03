package com.thailb.testcontainer.repositories;

import com.thailb.testcontainer.models.entities.Thread;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends MongoRepository<Thread, String> {
    @Query(value = "{}", count = true)
    Long countAllDocuments();
}
