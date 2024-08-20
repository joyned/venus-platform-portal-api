package com.venus.platform.core.service.impl;

import com.venus.platform.core.entity.Role;
import com.venus.platform.core.repository.RoleRepository;
import com.venus.platform.core.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
