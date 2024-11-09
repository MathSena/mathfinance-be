package com.mathsena.mathfinance.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IncomeDTO {
  private Long id;
  private String description;
  private BigDecimal amount;
  private LocalDate date;
}
