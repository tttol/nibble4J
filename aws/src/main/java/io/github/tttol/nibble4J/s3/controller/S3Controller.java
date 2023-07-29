package io.github.tttol.nibble4J.s3.controller;

import io.github.tttol.nibble4J.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("s3")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @GetMapping("ls")
    public String ls() {
        return s3Service.listObjects();
    }
}
