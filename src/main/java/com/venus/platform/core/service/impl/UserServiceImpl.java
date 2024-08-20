package com.venus.platform.core.service.impl;

import com.venus.platform.core.entity.Person;
import com.venus.platform.core.repository.PersonRepository;
import com.venus.platform.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PersonRepository personRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            Person person = personRepository.findByEmail(username);
            if (person == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return person;
        };
    }
}
