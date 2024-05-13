package com.transaction.service;

import com.transaction.dto.request.SearchMenuRequest;
import com.transaction.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    Menu create(Menu menu);
    Menu getById(String id);
    List<Menu> getALl(SearchMenuRequest searchMenuRequest);
}

