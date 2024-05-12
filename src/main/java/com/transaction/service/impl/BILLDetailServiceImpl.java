package com.transaction.service.impl;

import com.transaction.entity.BillDetail;
import com.transaction.service.BillDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BILLDetailServiceImpl implements BillDetailService {
    @Override
    public List<BillDetail> createBulk(List<BillDetail> billDetails) {
        return List.of();
    }
}
