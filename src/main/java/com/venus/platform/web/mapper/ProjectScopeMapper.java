package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.ProjectScope;
import com.venus.platform.web.dto.ProjectScopeDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProjectScopeMapper {

    ProjectScope toEntity(ProjectScopeDTO dto);
    ProjectScopeDTO toDTO(ProjectScope entity);
    List<ProjectScope> toEntity(List<ProjectScopeDTO> dto);
    List<ProjectScopeDTO> toDTO(List<ProjectScope> entity);
}
