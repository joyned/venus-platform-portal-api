package com.venus.platform.web.dto;

import com.venus.platform.core.model.WorkflowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalWorkflowDTO {
    private Long id;
    private ProjectDTO project;
    private WorkflowStatus status;
    private LocalDateTime createdAt;
    private List<WorkflowStepDTO> steps;
}
