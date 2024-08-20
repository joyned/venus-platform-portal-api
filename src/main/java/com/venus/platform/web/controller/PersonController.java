package com.venus.platform.web.controller;

import com.venus.platform.core.service.PersonService;
import com.venus.platform.web.dto.PersonDTO;
import com.venus.platform.web.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(personMapper.toDTO(personService.findAll()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PersonDTO>> search(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(personMapper.toDTO(personService.search(email)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findByEmail(@PathVariable Long id) {
        return ResponseEntity.ok(personMapper.toDTO(personService.findById(id)));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PersonDTO>> findByTeamId(@PathVariable Long teamId) {
        return ResponseEntity.ok(personMapper.toDTO(personService.findByTeamId(teamId)));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO person) {
        return ResponseEntity.ok(personMapper.toDTO(personService.savePerson(personMapper.toEntity(person))));
    }
}
