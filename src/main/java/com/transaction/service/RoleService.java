package com.transaction.service;

import com.transaction.constan.UserRole;
import com.transaction.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getOrSave(UserRole role);
}
