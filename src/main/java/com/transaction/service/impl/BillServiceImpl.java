package com.transaction.service.impl;

import com.transaction.dto.request.BillRequest;
import com.transaction.dto.response.BillResponse;
import com.transaction.entity.Customer;
import com.transaction.repository.BillDetailRepository;
import com.transaction.repository.BillRepository;
import com.transaction.service.BillDetailService;
import com.transaction.service.BillService;
import com.transaction.service.CustomerService;
import com.transaction.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final BillDetailService billDetailService;
    private final CustomerService customerService;
    private final MenuService menuService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BillResponse create(BillRequest request) {
        return null;
    }

    @Override
    public List<BillResponse> getAll() {
        return List.of();
    }
}
