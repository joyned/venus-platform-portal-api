package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.Project;
import com.venus.platform.web.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toEntity(ProjectDTO project);
    ProjectDTO toDTO(Project project);
    List<Project> toEntity(List<ProjectDTO> project);
    List<ProjectDTO> toDTO(List<Project> project);

}
