package com.transaction.service;

import com.transaction.entity.Menu;
import org.springframework.stereotype.Service;

@Service
public interface MenuService {
    Menu create(Menu menu);
}
