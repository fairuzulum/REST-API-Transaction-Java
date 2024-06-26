package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.dto.request.NewMenuRequest;
import com.transaction.dto.request.SearchMenuRequest;
import com.transaction.dto.response.CommonResponse;
import com.transaction.dto.response.MenuResponse;
import com.transaction.dto.response.PagingResponse;
import com.transaction.entity.Menu;
import com.transaction.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.MENU_API)
public class MenuController {

    final MenuService menuService;

    @PostMapping
    public ResponseEntity<CommonResponse<MenuResponse>> createMenu(@RequestBody NewMenuRequest menu) {
        menuService.create(menu);

        MenuResponse newMenu = MenuResponse.builder()
                .name(menu.getName())
                .price(menu.getPrice())
                .build();

        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("CREATED MENU SUCCESS")
                .data(newMenu)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Menu>>> getAllMenu(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,

            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,

            @RequestParam(name = "name", required = false) String name
    ){
        SearchMenuRequest request = SearchMenuRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();
        Page<Menu> allMenu = menuService.getALl(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allMenu.getTotalPages())
                .totalElements(allMenu.getTotalElements())
                .page(allMenu.getPageable().getPageNumber() + 1)
                .size(allMenu.getPageable().getPageSize())
                .hasNext(allMenu.hasNext())
                .hasPrevious(allMenu.hasPrevious())
                .build();


        CommonResponse<List<Menu>> response = CommonResponse.<List<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Data Found")
                .data(allMenu.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Menu>> update(@RequestBody Menu menu){
        Menu update = menuService.update(menu);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message("UPDATE MENU SUCCESS")
                .data(update)
                .build();
        return ResponseEntity.ok().body(response);
    }
}
