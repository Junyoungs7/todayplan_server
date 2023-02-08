package com.jun.todayplan.testController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/test")
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("test OK");
    }
}
