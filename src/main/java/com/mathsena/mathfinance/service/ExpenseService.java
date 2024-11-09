package com.mathsena.mathfinance.service;

import com.mathsena.mathfinance.dto.ExpenseDTO;
import com.mathsena.mathfinance.mapper.ExpenseMapper;
import com.mathsena.mathfinance.model.Expense;
import com.mathsena.mathfinance.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseService {

  private final ExpenseRepository expenseRepository;
  private final ExpenseMapper expenseMapper = ExpenseMapper.INSTANCE;

  public List<ExpenseDTO> findAll() {
    log.info("Finding all expenses");
    return expenseRepository.findAll().stream()
        .map(expenseMapper::expenseToExpenseDTO)
        .collect(Collectors.toList());
  }

  public Optional<ExpenseDTO> findById(Long id) {
    log.info("Fetching expense by id: {}", id);
    return expenseRepository.findById(id).map(expenseMapper::expenseToExpenseDTO);
  }

  public ExpenseDTO save(ExpenseDTO expenseDTO) {
    log.info("Saving new expense: {}", expenseDTO);
    Expense expense = expenseMapper.expenseDTOToExpense(expenseDTO);
    return expenseMapper.expenseToExpenseDTO(expenseRepository.save(expense));
  }

  public void deleteById(Long id) {
    log.info("Deleting expense with id: {}", id);
    expenseRepository.deleteById(id);
  }
}
