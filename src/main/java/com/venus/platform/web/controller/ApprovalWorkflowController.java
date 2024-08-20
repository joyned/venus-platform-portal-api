package com.venus.platform.web.controller;

import com.venus.platform.core.service.impl.ApprovalWorkflowService;
import com.venus.platform.web.dto.ApprovalWorkflowDTO;
import com.venus.platform.web.dto.WorkflowStepDTO;
import com.venus.platform.web.mapper.ApprovalWorkflowMapper;
import com.venus.platform.web.mapper.WorkflowStepMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/workflow/approval")
@RequiredArgsConstructor
public class ApprovalWorkflowController {

    private final ApprovalWorkflowService approvalWorkflowService;
    private final ApprovalWorkflowMapper approvalWorkflowMapper;
    private final WorkflowStepMapper workflowStepMapper;

    @GetMapping
    public ResponseEntity<List<ApprovalWorkflowDTO>> getWorkflow() {
        return ResponseEntity.ok(approvalWorkflowMapper.toDTO(approvalWorkflowService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApprovalWorkflowDTO> getWorkflow(@PathVariable Long id) {
        return ResponseEntity.ok(approvalWorkflowMapper.toDTO(approvalWorkflowService.findWorkflowById(id)));
    }

    @PostMapping("/approve")
    public ResponseEntity<ApprovalWorkflowDTO> approveWorkflow(@RequestBody WorkflowStepDTO dto) {
        approvalWorkflowService.approveStep(workflowStepMapper.toEntity(dto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject")
    public ResponseEntity<ApprovalWorkflowDTO> rejectWorkflow(@RequestBody WorkflowStepDTO dto) {
        approvalWorkflowService.rejectStep(workflowStepMapper.toEntity(dto));
        return ResponseEntity.ok().build();
    }

}
