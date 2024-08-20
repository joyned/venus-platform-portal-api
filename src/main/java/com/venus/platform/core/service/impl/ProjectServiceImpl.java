package com.venus.platform.core.service.impl;

import com.venus.platform.core.entity.Project;
import com.venus.platform.core.repository.ProjectRepository;
import com.venus.platform.core.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ApprovalWorkflowService approvalWorkflowService;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project findByProjectName(String projectName) {
        return projectRepository.findByName(projectName);
    }

    @Override
    @Transactional
    public Project save(Project project) {
        project = projectRepository.save(project);
        approvalWorkflowService.startWorkflow(project);
        return project;
    }
}
