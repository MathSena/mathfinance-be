package com.mathsena.mathfinance.repository;

import com.mathsena.mathfinance.model.PendingBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingBillRepository extends JpaRepository<PendingBill, Long> {}
