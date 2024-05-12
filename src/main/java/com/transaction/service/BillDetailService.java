package com.transaction.service;

import com.transaction.entity.BillDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillDetailService {
    List<BillDetail> createBulk(List<BillDetail> billDetails);
}
