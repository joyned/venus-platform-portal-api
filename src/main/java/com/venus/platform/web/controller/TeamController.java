package com.venus.platform.web.controller;

import com.venus.platform.core.service.TeamService;
import com.venus.platform.web.dto.TeamDTO;
import com.venus.platform.web.mapper.TeamMapper;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @GetMapping("/my-teams")
    public ResponseEntity<List<TeamDTO>> myTeams() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(teamMapper.toDTO(teamService.findByManager(userDetails.getUsername())));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<TeamDTO>> findAll() {
        return ResponseEntity.ok(teamMapper.toDTO(teamService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public ResponseEntity<TeamDTO> findById(@PathVariable Long id, @RequestParam(name = "fetchMembers", required = false) boolean fetchMembers) {
        return ResponseEntity.ok(teamMapper.toDTO(teamService.findById(id, fetchMembers)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TeamDTO> save(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamMapper.toDTO(teamService.save(teamMapper.toEntity(teamDTO))));
    }

}
