package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.ApprovalWorkflow;
import com.venus.platform.web.dto.ApprovalWorkflowDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ApprovalWorkflowMapper {
    ApprovalWorkflow toEntity(ApprovalWorkflowDTO dto);
    ApprovalWorkflowDTO toDTO(ApprovalWorkflow entity);
    List<ApprovalWorkflow> toEntity(List<ApprovalWorkflowDTO> dto);
    List<ApprovalWorkflowDTO> toDTO(List<ApprovalWorkflow> entity);
}
