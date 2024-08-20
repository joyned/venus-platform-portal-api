package com.venus.platform.core.service;

import com.venus.platform.core.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project findById(Long id);

    Project findByProjectName(String projectName);

    Project save(Project project);

}
