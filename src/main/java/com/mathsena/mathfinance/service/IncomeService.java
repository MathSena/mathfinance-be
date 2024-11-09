package com.mathsena.mathfinance.service;

import com.mathsena.mathfinance.dto.IncomeDTO;
import com.mathsena.mathfinance.mapper.IncomeMapper;
import com.mathsena.mathfinance.model.Income;
import com.mathsena.mathfinance.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper = IncomeMapper.INSTANCE;

    public List<IncomeDTO> findAll() {
        log.info("Fetching all incomes.");
        return incomeRepository.findAll()
                .stream()
                .map(incomeMapper::incomeToIncomeDTO)
                .collect(Collectors.toList());
    }

    public Optional<IncomeDTO> findById(Long id) {
        log.info("Fetching income with id: {}", id);
        return incomeRepository.findById(id)
                .map(incomeMapper::incomeToIncomeDTO);
    }

    public IncomeDTO save(IncomeDTO incomeDTO) {
        log.info("Saving new income: {}", incomeDTO);
        Income income = incomeMapper.incomeDTOToIncome(incomeDTO);
        return incomeMapper.incomeToIncomeDTO(incomeRepository.save(income));
    }

    public void deleteById(Long id) {
        log.info("Deleting income with id: {}", id);
        incomeRepository.deleteById(id);
    }
}
