package com.saurabh3034.connectSphere.ConnectionsService.controller;

import com.saurabh3034.connectSphere.ConnectionsService.entity.Person;
import com.saurabh3034.connectSphere.ConnectionsService.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class ConnectionsController {
    private final ConnectionsService connectionsService;

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId) {
        log.info("Getting connections for user {}", userId);
        List<Person> personList = connectionsService.getFirstDegreeConnections(userId);
        return ResponseEntity.ok(personList);
    }
}
