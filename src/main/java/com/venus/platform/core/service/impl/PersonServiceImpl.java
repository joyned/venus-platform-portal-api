package com.venus.platform.core.service.impl;

import com.venus.platform.core.entity.Person;
import com.venus.platform.core.repository.PersonRepository;
import com.venus.platform.core.service.PersonService;
import com.venus.platform.exception.InvalidEmailOrPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> search(String email) {
        return personRepository.findByEmailContains(email);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person findPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public List<Person> findByTeamId(Long teamId) {
        return personRepository.findByTeamId(teamId);
    }

    @Override
    public Person savePerson(Person person) {
        // TODO: melhorar
        if (StringUtils.hasText(person.getPassword())) {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
        }

        if (Objects.isNull(person.getId()) || person.getId() == 0) {
            person.setCreatedAt(LocalDateTime.now());
        }

        Person personTemp = findById(person.getId());
        personTemp.setName(person.getName());
        personTemp.setEmail(person.getEmail());
        personTemp.setActive(person.getActive());
        personTemp.setRole(person.getRole());
        personTemp.setTeam(person.getTeam());

        return personRepository.save(personTemp);
    }

    @Override
    public Person validatePerson(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        Person person = personRepository.findByEmail(email);
        if (Objects.isNull(person)) {
            log.error("Invalid email or password for email {}", email);
            throw new InvalidEmailOrPasswordException();
        }
        return person;
    }
}
