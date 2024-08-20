package com.venus.platform.core.service;

import com.venus.platform.core.entity.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    Team findById(Long id, boolean fetchMembers);

    Team save(Team team);

    List<Team> findByManager(String username);

}
