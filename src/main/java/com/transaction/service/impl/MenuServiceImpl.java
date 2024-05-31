package com.transaction.service.impl;

import com.transaction.dto.request.SearchMenuRequest;
import com.transaction.entity.Customer;
import com.transaction.entity.Menu;
import com.transaction.repository.MenuRepository;
import com.transaction.service.MenuService;
import com.transaction.specification.MenuSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;


    @Override
    public Menu create(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public Menu getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

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

    @Override
    public Menu update(Menu request) {
        findByIdOrThrowNotFound(request.getId());
        return menuRepository.saveAndFlush(request);
    }

    private Menu findByIdOrThrowNotFound(String id) {
        return menuRepository.findById(id).orElseThrow(() -> new  RuntimeException("Menu Not Found"));
    }
}
