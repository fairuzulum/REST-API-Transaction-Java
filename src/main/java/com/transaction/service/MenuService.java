package com.transaction.service;

import com.transaction.dto.request.NewMenuRequest;
import com.transaction.dto.request.SearchMenuRequest;
import com.transaction.dto.response.MenuResponse;
import com.transaction.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface MenuService {
    MenuResponse create(NewMenuRequest menu);
    Menu getById(String id);
    Page<Menu> getALl(SearchMenuRequest searchMenuRequest);

    Menu update(Menu request);

}

