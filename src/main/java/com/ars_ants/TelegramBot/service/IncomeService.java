package com.ars_ants.TelegramBot.service;

import com.ars_ants.TelegramBot.domain.Income;
import com.ars_ants.TelegramBot.repo.IncomeRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public Income create(Long userId, String category, float cost) {
        return incomeRepository.save(new Income(userId, cost, category, new Timestamp(System.currentTimeMillis())));
    }

    public void delete(Long incomeId) {
        incomeRepository.deleteById(incomeId);
    }

    public Income findById(Long id) throws Exception {
        Optional<Income> income = incomeRepository.findById(id);
        income.orElseThrow(() -> new Exception("Wrong income id"));
        return income.get();
    }

    public Set<Income> getAllByUserId(Long userId) {
        return incomeRepository.getAllByUserId(userId);
    }
}
