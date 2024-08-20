package com.venus.platform.core.service.impl;

import com.venus.platform.core.entity.Team;
import com.venus.platform.core.repository.TeamRepository;
import com.venus.platform.core.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(Long id, boolean fetchMembers) {
        Team team = teamRepository.findById(id).orElse(null);
        if (Objects.isNull(team)) {
            return null;
        }

        if (fetchMembers) {
            team.setMembers(team.getMembers());
        }
        return team;
    }

    @Override
    public Team save(Team team) {
        if (Objects.isNull(team.getId()) || team.getId() == 0) {
            team.setCreatedAt(LocalDateTime.now());
        }

        return teamRepository.save(team);
    }

    @Override
    public List<Team> findByManager(String username) {
        return teamRepository.findByManagerEmail(username);
    }
}
