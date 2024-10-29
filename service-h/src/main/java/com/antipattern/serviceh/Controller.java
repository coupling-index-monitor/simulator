package com.antipattern.serviceh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-h")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/path1")
    public ResponseEntity<String> path1() {
        logger.info("path1: execution started");

        logger.info("path1: execution ended");
        return ResponseEntity.ok("response from /service-h/path1");
    }
}
