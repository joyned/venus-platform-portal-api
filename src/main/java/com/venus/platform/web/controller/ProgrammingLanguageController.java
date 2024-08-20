package com.venus.platform.web.controller;

import com.venus.platform.core.service.ProgrammingLanguageService;
import com.venus.platform.web.dto.ProgrammingLanguageDTO;
import com.venus.platform.web.mapper.ProgrammingLanguageMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/programming-language")
@RequiredArgsConstructor
public class ProgrammingLanguageController {

    private final ProgrammingLanguageService programmingLanguageService;
    private final ProgrammingLanguageMapper programmingLanguageMapper;

    @GetMapping
    public ResponseEntity<List<ProgrammingLanguageDTO>> findAll() {
        return ResponseEntity.ok(programmingLanguageMapper.toDTO(programmingLanguageService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingLanguageDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(programmingLanguageMapper.toDTO(programmingLanguageService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProgrammingLanguageDTO> save(@RequestBody @Valid ProgrammingLanguageDTO programmingLanguageDTO) {
        return ResponseEntity.ok(programmingLanguageMapper.toDTO(programmingLanguageService.save(programmingLanguageMapper.toEntity(programmingLanguageDTO))));
    }
}
