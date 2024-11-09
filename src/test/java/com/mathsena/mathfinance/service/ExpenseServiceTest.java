package com.mathsena.mathfinance.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mathsena.mathfinance.dto.ExpenseDTO;
import com.mathsena.mathfinance.model.Expense;
import com.mathsena.mathfinance.repository.ExpenseRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class ExpenseServiceTest {

  @Mock private ExpenseRepository expenseRepository;

  @InjectMocks private ExpenseService expenseService;

  @Test
  public void testFindAllExpenses() {
    // Arrange
    Expense expense1 = createExpense("Milk", new BigDecimal("10.5"), LocalDate.now());
    Expense expense2 = createExpense("Bread", new BigDecimal("2.0"), LocalDate.now());

    when(expenseRepository.findAll()).thenReturn(Arrays.asList(expense1, expense2));

    // Act
    List<ExpenseDTO> expenses = expenseService.findAll();

    // Assert
    assertEquals(2, expenses.size());
    assertEquals("Milk", expenses.get(0).getDescription());
    assertEquals(new BigDecimal("10.5"), expenses.get(0).getAmount());
    assertEquals("Bread", expenses.get(1).getDescription());
    assertEquals(new BigDecimal("2.0"), expenses.get(1).getAmount());

    verify(expenseRepository, times(1)).findAll();
  }

  private Expense createExpense(String description, BigDecimal amount, LocalDate date) {
    Expense expense = new Expense();
    expense.setDescription(description);
    expense.setAmount(amount);
    expense.setDate(date);
    return expense;
  }
}
