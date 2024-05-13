package com.transaction.service.impl;

import com.transaction.dto.request.BillDetailRequest;
import com.transaction.dto.request.BillRequest;
import com.transaction.dto.response.BillDetailResponse;
import com.transaction.dto.response.BillResponse;
import com.transaction.entity.Bill;
import com.transaction.entity.BillDetail;
import com.transaction.entity.Customer;
import com.transaction.entity.Menu;
import com.transaction.repository.BillDetailRepository;
import com.transaction.repository.BillRepository;
import com.transaction.service.BillDetailService;
import com.transaction.service.BillService;
import com.transaction.service.CustomerService;
import com.transaction.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        Customer customer = customerService.getById(request.getCustomerId());

        Bill trx = Bill.builder()
                .customer(customer)
                .transDate(new Date())
                .build();

        billRepository.save(trx);

        List<BillDetail> trxDetails = request.getBillDetails().stream()
                .map(detailRequest -> {
                    Menu menu = menuService.getById(detailRequest.getMenuId());

                    return BillDetail.builder()
                            .menu(menu)
                            .bill(trx)
//                            .tableId(detailRequest.getTableId())
//                            .transTypeId(detailRequest.getTransTypeId())
                            .qty(detailRequest.getQty())
                            .menuPrice(menu.getPrice())
                            .build();
                }).collect(Collectors.toList());

        List<BillDetailResponse> trxDetailResponses = trxDetails.stream()
                .map(detail -> BillDetailResponse.builder()
                        .id(detail.getId())
                        .menuId(detail.getMenu().getId())
                        .menuPrice(detail.getMenuPrice())
                        .quantity(detail.getQty())
                        .build()
                ).collect(Collectors.toList());

        billDetailService.createBulk(trxDetails);
        trx.setBillDetails(trxDetails);

        return BillResponse.builder()
                .id(trx.getId())
                .customerId(trx.getCustomer().getId())
                .transDate(trx.getTransDate())
                .billDetails(trxDetailResponses)
                .build();
    }

    @Override
    public List<BillResponse> getAll() {

        return null;
    }
}
