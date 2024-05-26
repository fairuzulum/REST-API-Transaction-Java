package com.transaction.service;

import com.transaction.dto.request.SearchMenuRequest;
import com.transaction.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    Menu create(Menu menu);
    Menu getById(String id);
    Page<Menu> getALl(SearchMenuRequest searchMenuRequest);
}

