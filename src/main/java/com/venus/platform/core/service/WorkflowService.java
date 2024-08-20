package com.venus.platform.core.service;

import com.venus.platform.core.entity.Project;
import com.venus.platform.core.entity.WorkflowStep;

import java.util.List;

public interface WorkflowService<T> {

    List<T> findAll();

    T findWorkflowById(Long id);

    T startWorkflow(Project project);

    WorkflowStep currentStep(T workflow);

    T endWorkflow(T workflow);

    WorkflowStep approveStep(WorkflowStep step);

    WorkflowStep rejectStep(WorkflowStep step);

}
