package com.mathsena.mathfinance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.mathsena.mathfinance.dto.PendingBillDTO;
import com.mathsena.mathfinance.mapper.PendingBillMapper;
import com.mathsena.mathfinance.model.PendingBill;
import com.mathsena.mathfinance.repository.PendingBillRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
public class PendingBillServiceTest {

  @Autowired private TestEntityManager entityManager;

  @MockBean private PendingBillRepository pendingBillRepository;

  @MockBean private PendingBillMapper pendingBillMapper;

  @Autowired private PendingBillService pendingBillService;

  @Test
  public void testFindAllNoBills() {
    when(pendingBillRepository.findAll()).thenReturn(Collections.emptyList());
    List<PendingBillDTO> actualResult = pendingBillService.findAll();
    assertThat(actualResult).isEmpty();
  }

  @Test
  public void testFindAllWithBills() {
    List<PendingBill> testBills = createTestBills();
    when(pendingBillRepository.findAll()).thenReturn(testBills);

    PendingBillDTO pendingBillDTO = createTestBillDTO();
    when(pendingBillMapper.pendingBillToPendingBillDTO(testBills.get(0)))
        .thenReturn(pendingBillDTO);

    List<PendingBillDTO> actualResult = pendingBillService.findAll();
    assertThat(actualResult).isEqualTo(Collections.singletonList(pendingBillDTO));
  }

  private List<PendingBill> createTestBills() {
    return Collections.singletonList(new PendingBill());
  }

  private PendingBillDTO createTestBillDTO() {
    PendingBillDTO pendingBillDTO = new PendingBillDTO();
    pendingBillDTO.setDescription("test bill");
    pendingBillDTO.setAmount(new BigDecimal("200.00"));
    pendingBillDTO.setDueDate(LocalDate.now());
    return pendingBillDTO;
  }
}
