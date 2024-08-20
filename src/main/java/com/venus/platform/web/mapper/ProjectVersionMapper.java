package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.ProjectVersion;
import com.venus.platform.web.dto.ProjectVersionDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProjectVersionMapper {
    ProjectVersion toEntity(ProjectVersionDTO dto);
    ProjectVersionDTO toDTO(ProjectVersion entity);
    List<ProjectVersion> toEntity(List<ProjectVersionDTO> dto);
    List<ProjectVersionDTO> toDTO(List<ProjectVersion> entity);
}
