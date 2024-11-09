package com.mathsena.mathfinance.controller;

import com.mathsena.mathfinance.dto.PendingBillDTO;
import com.mathsena.mathfinance.service.PendingBillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/pending-bills")
@RequiredArgsConstructor
@Slf4j
public class PendingBillController {

  private final PendingBillService pendingBillService;

  @GetMapping
  public ResponseEntity<List<PendingBillDTO>> getAllPendingBills() {
    log.info("Received GET request to list all pending bills.");
    return ResponseEntity.ok(pendingBillService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<PendingBillDTO>> getPendingBillById(@PathVariable Long id) {
    log.info("Received GET request to fetch pending bill with id: {}", id);
    return ResponseEntity.ok(pendingBillService.findById(id));
  }

  @PostMapping
  public ResponseEntity<PendingBillDTO> createPendingBill(
      @Valid @RequestBody PendingBillDTO pendingBillDTO) {
    log.info("Received POST request to create new pending bill.");
    return new ResponseEntity<>(pendingBillService.save(pendingBillDTO), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePendingBill(@PathVariable Long id) {
    log.info("Received DELETE request for pending bill with id: {}", id);
    pendingBillService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
