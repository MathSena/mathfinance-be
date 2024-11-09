package com.mathsena.mathfinance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.mathsena.mathfinance.dto.IncomeDTO;
import com.mathsena.mathfinance.mapper.IncomeMapper;
import com.mathsena.mathfinance.model.Income;
import com.mathsena.mathfinance.repository.IncomeRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IncomeServiceTest {
  @Mock private IncomeRepository incomeRepository;
  @InjectMocks private IncomeService incomeService;

  private final IncomeMapper incomeMapper = IncomeMapper.INSTANCE;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void shouldReturnAllIncomes() {
    // given
    Income salaryIncome = createIncome(1L, "Salary", new BigDecimal(5000), LocalDate.now());
    Income interestIncome =
        createIncome(2L, "Interest", new BigDecimal(1000), LocalDate.of(2022, 9, 1));

    List<Income> incomes = Arrays.asList(salaryIncome, interestIncome);
    List<IncomeDTO> incomeDTOs = incomes.stream().map(incomeMapper::incomeToIncomeDTO).toList();

    // when
    when(incomeRepository.findAll()).thenReturn(incomes);

    // then
    List<IncomeDTO> result = incomeService.findAll();
    assertEquals(incomeDTOs.size(), result.size());
    assertEquals(incomeDTOs.get(0).getDescription(), result.get(0).getDescription());
    assertEquals(incomeDTOs.get(1).getDescription(), result.get(1).getDescription());
  }

  private Income createIncome(
      Long id, String description, BigDecimal amount, LocalDate dateIncome) {
    Income income = new Income();
    income.setId(id);
    income.setDescription(description);
    income.setAmount(amount);
    income.setDateIncome(dateIncome);
    return income;
  }
}
