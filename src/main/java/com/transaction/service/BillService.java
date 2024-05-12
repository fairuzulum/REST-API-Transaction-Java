package com.transaction.service;

import com.transaction.dto.request.BillRequest;
import com.transaction.dto.response.BillResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    BillResponse create(BillRequest request);

    List<BillResponse> getAll();
}
