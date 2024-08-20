package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.WorkflowStep;
import com.venus.platform.web.dto.WorkflowStepDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface WorkflowStepMapper {
    WorkflowStep toEntity(WorkflowStepDTO dto);
    WorkflowStepDTO toDTO(WorkflowStep entity);
    List<WorkflowStep> toEntity(List<WorkflowStepDTO> dto);
    List<WorkflowStepDTO> toDTO(List<WorkflowStep> entity);
}
