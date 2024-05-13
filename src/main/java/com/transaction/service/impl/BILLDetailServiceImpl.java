package com.transaction.service.impl;

import com.transaction.entity.BillDetail;
import com.transaction.repository.BillDetailRepository;
import com.transaction.service.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BILLDetailServiceImpl implements BillDetailService {
    private final BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> createBulk(List<BillDetail> billDetails) {
        return billDetailRepository.saveAllAndFlush(billDetails);
    }
}
