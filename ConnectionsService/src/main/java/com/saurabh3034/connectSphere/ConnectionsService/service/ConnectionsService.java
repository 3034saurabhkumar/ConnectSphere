package com.saurabh3034.connectSphere.ConnectionsService.service;

import com.saurabh3034.connectSphere.ConnectionsService.entity.Person;
import com.saurabh3034.connectSphere.ConnectionsService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionsService {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnections(Long userId) {
        log.info("Getting first degree connections for user with id {}", userId);
        System.out.println(personRepository.getFirstDegreeConnections(userId));
        return personRepository.getFirstDegreeConnections(userId);
    }
}
