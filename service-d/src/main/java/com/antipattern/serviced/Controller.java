package com.antipattern.serviced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/service-d")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final RestTemplate restTemplate;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/path1")
    public ResponseEntity<String> path1() {
        logger.info("path1: execution started");

        String response = restTemplate.getForObject("http://service-b:9091/service-b/path2", String.class);

        logger.info("path1: execution ended");
        return ResponseEntity.ok("response from /service-a/path1: " + response);
    }

    @GetMapping("/path2")
    public ResponseEntity<String> path2() {
        logger.info("path2: execution started");

        CompletableFuture<String> response1Future = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://service-h:9097/service-h/path1", String.class)
        );

        CompletableFuture<String> response2Future = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://service-i:9098/service-i/path1", String.class)
        );

        CompletableFuture<Void> allOf = CompletableFuture.allOf(response1Future, response2Future);
        allOf.join();

        String response1 = response1Future.join();
        String response2 = response2Future.join();

        logger.info("path2: execution ended");
        return ResponseEntity.ok("response from /service-d/path2: " + response1 + ", " + response2);
    }
}