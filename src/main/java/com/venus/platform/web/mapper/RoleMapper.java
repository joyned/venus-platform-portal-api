package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.Role;
import com.venus.platform.web.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {
    List<RoleDTO> toDTO(List<Role> entity);
}
