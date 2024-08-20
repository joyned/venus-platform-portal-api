package com.venus.platform.core.service.impl;

import com.venus.platform.core.entity.ApprovalWorkflow;
import com.venus.platform.core.entity.Project;
import com.venus.platform.core.entity.WorkflowStep;
import com.venus.platform.core.model.WorkflowStatus;
import com.venus.platform.core.repository.ApprovalWorkflowRepository;
import com.venus.platform.core.service.WorkflowService;
import com.venus.platform.core.service.WorkflowStepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApprovalWorkflowService implements WorkflowService<ApprovalWorkflow> {

    private final ApprovalWorkflowRepository approvalWorkflowRepository;
    private final WorkflowStepService workflowStepService;

    @Override
    public List<ApprovalWorkflow> findAll() {
        return approvalWorkflowRepository.findAll();
    }

    @Override
    public ApprovalWorkflow findWorkflowById(Long id) {
        return approvalWorkflowRepository.findById(id).orElse(null);
    }

    @Override
    public ApprovalWorkflow startWorkflow(Project project) {
        log.info("Project {} is starting a new workflow. Started/Created by {}", project.getName(), project.getCreatedBy());
        ApprovalWorkflow approvalWorkflow = new ApprovalWorkflow();
        approvalWorkflow.setProject(project);
        approvalWorkflow.setStatus(WorkflowStatus.IN_PROGRESS);
        approvalWorkflow = approvalWorkflowRepository.save(approvalWorkflow);
        workflowStepService.addStep(approvalWorkflow, 1, WorkflowStatus.PENDING);
        return approvalWorkflow;
    }

    @Override
    public WorkflowStep currentStep(ApprovalWorkflow workflow) {
        List<WorkflowStep> steps = workflowStepService.findByWorkflowId(workflow.getId());
        for (WorkflowStep step : steps) {
            if (step.getStatus() == WorkflowStatus.PENDING) {
                return step;
            }
        }
        return null;
    }

    @Override
    public ApprovalWorkflow endWorkflow(ApprovalWorkflow workflow) {
        log.info("Workflow for project {} has been completed with status {}", workflow.getProject().getName(), workflow.getStatus());
        workflow.setStatus(WorkflowStatus.COMPLETED);
        return approvalWorkflowRepository.save(workflow);
    }

    @Override
    public WorkflowStep approveStep(WorkflowStep step) {
        return workflowStepService.approveStep(step);
    }

    @Override
    public WorkflowStep rejectStep(WorkflowStep step) {
        step = workflowStepService.rejectStep(step);
        ApprovalWorkflow approvalWorkflow = step.getWorkflow();
        approvalWorkflow.setStatus(WorkflowStatus.REJECTED);
        endWorkflow(step.getWorkflow());
        return step;
    }

}
