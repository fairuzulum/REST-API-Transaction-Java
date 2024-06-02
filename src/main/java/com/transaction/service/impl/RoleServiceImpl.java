package com.transaction.service.impl;

import com.transaction.constan.UserRole;
import com.transaction.entity.Role;
import com.transaction.repository.RoleRepository;
import com.transaction.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(UserRole role) {
        return roleRepository.findByRole(role).orElseGet(() -> roleRepository.saveAndFlush(
                Role.builder()
                        .role(role)
                        .build()
        ));
    }
}
