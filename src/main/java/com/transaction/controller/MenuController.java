package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.entity.Menu;
import com.transaction.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.MENU_API)
public class MenuController {

    final MenuService menuService;

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.create(menu);
    }
}
