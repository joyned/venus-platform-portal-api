package com.venus.platform.web.dto;

import com.venus.platform.core.model.WorkflowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowStepDTO {
    private Long id;
    private Long workflowId;
    private Long approverId;
    private Integer stepOrder;
    private WorkflowStatus status;
    private LocalDateTime createdAt;
}
