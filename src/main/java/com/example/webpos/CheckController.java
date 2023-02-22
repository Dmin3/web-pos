package com.example.webpos;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CheckController {
    @GetMapping("/")
    public String check() {
        log.info("새롭게 배포 완료!!!!!!!!!");
        return "OK";
    }

    @GetMapping("/test")
    public String check2() {
        return "Hello";
    }
}
