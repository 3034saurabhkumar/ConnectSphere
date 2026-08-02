package com.saurabh3034.connectSphere.ConnectionsService.repository;

import com.saurabh3034.connectSphere.ConnectionsService.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Optional<Person> findByUserId(Long userId);

    @Query("match (personA:Person) -[:CONNECTED_TO]- (personB:Person)" +
            "where personA.userId = $userId " +
            "return personB")
    List<Person> getFirstDegreeConnections(Long userId);


}
