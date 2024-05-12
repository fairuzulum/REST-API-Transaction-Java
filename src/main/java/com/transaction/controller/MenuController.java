package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.dto.request.SearchMenuRequest;
import com.transaction.entity.Menu;
import com.transaction.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.MENU_API)
public class MenuController {

    final MenuService menuService;

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.create(menu);
    }

    @GetMapping
    public List<Menu> getAllMenus(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price", required = false) Long price
    ) {
        SearchMenuRequest searchMenuRequest = SearchMenuRequest.builder()
                .name(name)
                .price(price)
                .build();
        return menuService.getALl(searchMenuRequest);
    }
}
