package com.venus.platform.core.service;

import com.venus.platform.core.entity.ApprovalWorkflow;
import com.venus.platform.core.entity.WorkflowStep;
import com.venus.platform.core.model.WorkflowStatus;

import java.util.List;

public interface WorkflowStepService {

    WorkflowStep approveStep(WorkflowStep step);

    WorkflowStep rejectStep(WorkflowStep step);

    //TODO: deixar generico
    WorkflowStep addStep(ApprovalWorkflow approvalWorkflow, int order, WorkflowStatus status);

    List<WorkflowStep> findByWorkflowId(Long workflowId);
}
