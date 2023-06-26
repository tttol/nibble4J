package io.github.tttol.nibble4J;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class NibbleController {
    @GetMapping
    public String demo() {
        return "Nibble4J demo";
    }
}
