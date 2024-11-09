package com.mathsena.mathfinance.service;

import com.mathsena.mathfinance.dto.PendingBillDTO;
import com.mathsena.mathfinance.mapper.PendingBillMapper;
import com.mathsena.mathfinance.model.PendingBill;
import com.mathsena.mathfinance.repository.PendingBillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PendingBillService {

  private final PendingBillRepository pendingBillRepository;
  private final PendingBillMapper pendingBillMapper = PendingBillMapper.INSTANCE;

  public List<PendingBillDTO> findAll() {
    log.info("Fetching all pending bills.");
    return pendingBillRepository.findAll().stream()
        .map(pendingBillMapper::pendingBillToPendingBillDTO)
        .collect(Collectors.toList());
  }

  public Optional<PendingBillDTO> findById(Long id) {
    log.info("Fetching pending bill with id: {}", id);
    return pendingBillRepository.findById(id).map(pendingBillMapper::pendingBillToPendingBillDTO);
  }

  public PendingBillDTO save(PendingBillDTO pendingBillDTO) {
    log.info("Saving new pending bill: {}", pendingBillDTO);
    PendingBill pendingBill = pendingBillMapper.pendingBillDTOToPendingBill(pendingBillDTO);
    return pendingBillMapper.pendingBillToPendingBillDTO(pendingBillRepository.save(pendingBill));
  }

  public void deleteById(Long id) {
    log.info("Deleting pending bill with id: {}", id);
    pendingBillRepository.deleteById(id);
  }
}
