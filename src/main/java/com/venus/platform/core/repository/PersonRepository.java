package com.venus.platform.core.repository;

import com.venus.platform.core.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByEmail(String email);

    List<Person> findByEmailContains(String email);

    List<Person> findByTeamId(Long teamId);
}
