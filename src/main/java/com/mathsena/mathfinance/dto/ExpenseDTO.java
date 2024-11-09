package com.mathsena.mathfinance.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseDTO {
  private Long id;
  private String description;
  private BigDecimal amount;
  private String category;
  private LocalDate date;
}
