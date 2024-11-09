package com.mathsena.mathfinance.controller;

import com.mathsena.mathfinance.dto.IncomeDTO;
import com.mathsena.mathfinance.service.IncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/incomes")
@RequiredArgsConstructor
@Slf4j
public class IncomeController {

  private final IncomeService incomeService;

  @GetMapping
  public ResponseEntity<List<IncomeDTO>> getAllIncomes() {
    log.info("Received GET request to list all incomes.");
    return ResponseEntity.ok(incomeService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<IncomeDTO>> getIncomeById(@PathVariable Long id) {
    log.info("Received GET request to fetch income with id: {}", id);
    return ResponseEntity.ok(incomeService.findById(id));
  }

  @PostMapping
  public ResponseEntity<IncomeDTO> createIncome(@Valid @RequestBody IncomeDTO incomeDTO) {
    log.info("Received POST request to create new income.");
    return new ResponseEntity<>(incomeService.save(incomeDTO), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
    log.info("Received DELETE request for income with id: {}", id);
    incomeService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
