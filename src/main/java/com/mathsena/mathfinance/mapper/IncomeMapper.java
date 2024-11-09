package com.mathsena.mathfinance.mapper;

import com.mathsena.mathfinance.dto.IncomeDTO;
import com.mathsena.mathfinance.model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncomeMapper {
  IncomeMapper INSTANCE = Mappers.getMapper(IncomeMapper.class);

  IncomeDTO incomeToIncomeDTO(Income income);

  Income incomeDTOToIncome(IncomeDTO incomeDTO);
}
