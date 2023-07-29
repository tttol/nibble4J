package io.github.tttol.nibble4J.s3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("s3")
public class S3Controller {
    @GetMapping
    public String demo() {
        return "S3 demo";
    }
}
