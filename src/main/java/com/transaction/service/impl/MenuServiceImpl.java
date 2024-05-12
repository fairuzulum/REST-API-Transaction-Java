package com.transaction.service.impl;

import com.transaction.entity.Menu;
import com.transaction.repository.MenuRepository;
import com.transaction.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    final MenuRepository menuRepository;

    @Override
    public Menu create(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }
}
