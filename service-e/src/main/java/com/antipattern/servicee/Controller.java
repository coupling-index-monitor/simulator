package com.antipattern.servicee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service-e")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final RestTemplate restTemplate;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/path1")
    public ResponseEntity<String> path1() {
        logger.info("path1: execution started");

        logger.info("path1: execution ended");
        return ResponseEntity.ok("response from /path1");
    }

    @GetMapping("/path2")
    public ResponseEntity<String> path2() {
        logger.info("path2: execution started");

        String response = restTemplate.getForObject("http://service-d:9093/service-d/path2", String.class);

        logger.info("path2: execution ended");
        return ResponseEntity.ok("response from /service-e/path2: " + response);
    }
}