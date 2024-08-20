package com.venus.platform.core.service.impl;

import com.venus.platform.core.entity.ApprovalWorkflow;
import com.venus.platform.core.entity.WorkflowStep;
import com.venus.platform.core.model.WorkflowStatus;
import com.venus.platform.core.repository.WorkflowStepRepository;
import com.venus.platform.core.service.WorkflowStepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkflowStepServiceImpl implements WorkflowStepService {

    private final WorkflowStepRepository workflowStepRepository;

    @Override
    public WorkflowStep approveStep(WorkflowStep step) {
        log.info("Step {} has been approved by {}", step.getId(), step.getApprover());
        step.setStatus(WorkflowStatus.APPROVED);
        return workflowStepRepository.save(step);
    }

    @Override
    public WorkflowStep rejectStep(WorkflowStep step) {
        log.info("Step {} has been rejected by {}", step.getId(), step.getApprover());
        step.setStatus(WorkflowStatus.REJECTED);
        return workflowStepRepository.save(step);
    }

    @Override
    public WorkflowStep addStep(ApprovalWorkflow approvalWorkflow, int order, WorkflowStatus status) {
        WorkflowStep workflowStep = new WorkflowStep();
        workflowStep.setWorkflow(approvalWorkflow);
        workflowStep.setStepOrder(order);
        workflowStep.setStatus(status);
        return workflowStepRepository.save(workflowStep);
    }

    @Override
    public List<WorkflowStep> findByWorkflowId(Long workflowId) {
        return workflowStepRepository.findByWorkflowId(workflowId);
    }
}
