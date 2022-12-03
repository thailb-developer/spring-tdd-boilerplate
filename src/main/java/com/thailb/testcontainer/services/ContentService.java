package com.thailb.testcontainer.services;

import com.thailb.testcontainer.models.entities.CreateThreadDTO;
import com.thailb.testcontainer.models.entities.Thread;
import com.thailb.testcontainer.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContentService {
    private final ThreadRepository threadRepository;

    @Autowired
    public ContentService(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    public Thread createThread(CreateThreadDTO createThreadDTO) {
        return this.threadRepository.save(Thread.from(createThreadDTO));
    }

    @Transactional(readOnly = true)
    public Long countDocument() {
        return this.threadRepository.countAllDocuments();
    }
}
