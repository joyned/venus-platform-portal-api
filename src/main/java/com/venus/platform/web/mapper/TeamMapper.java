package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.Team;
import com.venus.platform.web.dto.TeamDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDTO toDTO(Team entity);
    Team toEntity(TeamDTO dto);
    List<TeamDTO> toDTO(List<Team> entity);
    List<Team> toEntity(List<TeamDTO> dto);
}
