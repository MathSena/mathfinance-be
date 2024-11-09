package com.mathsena.mathfinance.controller;

import com.mathsena.mathfinance.dto.ExpenseDTO;
import com.mathsena.mathfinance.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/expenses")
@RequiredArgsConstructor
@Slf4j
public class ExpenseController {

  private final ExpenseService expenseService;

  @GetMapping
  public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
    log.info("Received GET request to list all expenses.");
    return ResponseEntity.ok(expenseService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<ExpenseDTO>> getExpenseById(@PathVariable Long id) {
    log.info("Received GET request to fetch expense with id: {}", id);
    return ResponseEntity.ok(expenseService.findById(id));
  }

  @PostMapping
  public ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
    log.info("Received POST request to create new expense.");
    return new ResponseEntity<>(expenseService.save(expenseDTO), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
    log.info("Received DELETE request for expense with id: {}", id);
    expenseService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
