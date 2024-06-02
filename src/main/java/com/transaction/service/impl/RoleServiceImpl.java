package com.transaction.service.impl;

import com.transaction.constan.UserRole;
import com.transaction.entity.Role;
import com.transaction.repository.RoleRepository;
import com.transaction.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(UserRole role) {
        return roleRepository.findByRole(role).orElseGet(() -> roleRepository.saveAndFlush(
                Role.builder()
                        .role(role)
                        .build()
        ));
    }
}
