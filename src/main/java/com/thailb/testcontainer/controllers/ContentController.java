package com.thailb.testcontainer.controllers;

import com.thailb.testcontainer.models.entities.CreateThreadDTO;
import com.thailb.testcontainer.models.entities.Thread;
import com.thailb.testcontainer.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("content")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping(value = "/thread", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Thread> createThread(@RequestBody CreateThreadDTO threadInput) {
        return ResponseEntity.ok(this.contentService.createThread(threadInput));
    }

    @GetMapping(value = "/thread", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> countDocument() {
        return ResponseEntity.ok(this.contentService.countDocument());
    }
}
