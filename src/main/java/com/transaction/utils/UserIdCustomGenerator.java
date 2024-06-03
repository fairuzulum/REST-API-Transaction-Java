package com.transaction.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

@Component
public class UserIdCustomGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        String prefix = "U";
        String query = "SELECT COALESCE(MAX(CAST(SUBSTRING(id, 2) AS INTEGER)), 0) FROM m_user_account";
        Integer max = (Integer) sharedSessionContractImplementor.createNativeQuery(query).getSingleResult();
        int nextId = (max == null ? 1 : max + 1);
        return prefix + String.format("%03d", nextId);
    }
}
