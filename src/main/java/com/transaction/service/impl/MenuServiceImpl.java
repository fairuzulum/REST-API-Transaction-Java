package com.transaction.service.impl;

import com.transaction.dto.request.NewMenuRequest;
import com.transaction.dto.request.SearchMenuRequest;
import com.transaction.dto.response.MenuResponse;
import com.transaction.entity.Menu;
import com.transaction.entity.UserAccount;
import com.transaction.repository.MenuRepository;
import com.transaction.service.MenuService;
import com.transaction.service.UserService;
import com.transaction.specification.MenuSpecification;
import com.transaction.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ValidationUtil validationUtil;
    private final UserService userService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public MenuResponse create(NewMenuRequest request) {
        validationUtil.validate(request);

        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getUsername().equals("admin")){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied");
        }

        Menu menu = Menu.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
       menuRepository.saveAndFlush(menu);

       return  MenuResponse.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Menu getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Menu> getALl(SearchMenuRequest request) {
        if (request.getPage() <= 0){
            request.setPage(1);
        }

        String validSortBy;
        if("name".equalsIgnoreCase(request.getSortBy()) || "price".equalsIgnoreCase(request.getSortBy())){
            validSortBy = request.getSortBy();
        }else {
            validSortBy = "name";
        }

        Sort sortBy = Sort.by(Sort.Direction.fromString(request.getDirection()), validSortBy);


        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sortBy);


        Specification<Menu> specification = MenuSpecification.getSpecification(request);
        return menuRepository.findAll(specification, pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Menu update(Menu request) {
        findByIdOrThrowNotFound(request.getId());
        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getUsername().equals("admin")){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied");
        }
        return menuRepository.saveAndFlush(request);
    }

    private Menu findByIdOrThrowNotFound(String id) {
        return menuRepository.findById(id).orElseThrow(() -> new  RuntimeException("Menu Not Found"));
    }
}
