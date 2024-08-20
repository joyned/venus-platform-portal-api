package com.venus.platform.core.service;

import com.venus.platform.core.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    List<Person> search(String email);

    Person findById(Long id);

    Person findPersonByEmail(String email);

    List<Person> findByTeamId(Long teamId);

    Person savePerson(Person person);

    Person validatePerson(String email, String password);
}
