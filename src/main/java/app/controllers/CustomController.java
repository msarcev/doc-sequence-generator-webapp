package app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

    @RequestMapping("/greet")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
