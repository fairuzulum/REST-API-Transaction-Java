package com.transaction.service.impl;

import com.transaction.dto.request.SearchMenuRequest;
import com.transaction.entity.Menu;
import com.transaction.repository.MenuRepository;
import com.transaction.service.MenuService;
import com.transaction.specification.MenuSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;


    @Override
    public Menu create(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public List<Menu> getALl(SearchMenuRequest request) {
        Specification<Menu> menuSpecification = MenuSpecification.getSpecification(request);
        if(request.getName() == null && request.getPrice() == null) {
            return menuRepository.findAll();
        }
        return menuRepository.findAll(menuSpecification);
    }
}
