package com.mathsena.mathfinance.mapper;

import com.mathsena.mathfinance.dto.ExpenseDTO;
import com.mathsena.mathfinance.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseMapper {
  ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

  ExpenseDTO expenseToExpenseDTO(Expense expense);

  Expense expenseDTOToExpense(ExpenseDTO expenseDTO);
}
