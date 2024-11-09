package com.mathsena.mathfinance.mapper;

import com.mathsena.mathfinance.dto.PendingBillDTO;
import com.mathsena.mathfinance.model.PendingBill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PendingBillMapper {
  PendingBillMapper INSTANCE = Mappers.getMapper(PendingBillMapper.class);

  PendingBillDTO pendingBillToPendingBillDTO(PendingBill pendingBill);

  PendingBill pendingBillDTOToPendingBill(PendingBillDTO pendingBillDTO);
}
