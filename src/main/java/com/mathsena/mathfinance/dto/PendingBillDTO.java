package com.mathsena.mathfinance.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PendingBillDTO {
  private Long id;

  @NotBlank(message = "Description is mandatory")
  private String description;

  @NotNull(message = "Amount is mandatory")
  @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
  private BigDecimal amount;

  private boolean paid;

  @NotNull(message = "Due date is mandatory")
  private LocalDate dueDate;
}
