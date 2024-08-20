package com.venus.platform.web.controller;

import com.venus.platform.core.service.ProjectService;
import com.venus.platform.web.dto.ProjectDTO;
import com.venus.platform.web.mapper.ProjectMapper;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAll(@PathVariable(name = "id", required = false) Long id,
                                                    @PathVariable(name = "name", required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(List.of(projectMapper.toDTO(projectService.findById(id))));
        }

        if (name != null) {
            return ResponseEntity.ok(List.of(projectMapper.toDTO(projectService.findByProjectName(name))));
        }

        return ResponseEntity.ok(projectMapper.toDTO(projectService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> save(ProjectDTO projectDTO) {
        return ResponseEntity.ok(projectMapper.toDTO(projectService.save(projectMapper.toEntity(projectDTO))));
    }
}
