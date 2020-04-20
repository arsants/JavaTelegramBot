package com.ars_ants.TelegramBot.service;

import com.ars_ants.TelegramBot.model.Income;
import com.ars_ants.TelegramBot.repo.IncomeRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public Income create(Long userId, float cost, String category) {
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

    public List<Income> getAllByUserId(Long userId) {
        return incomeRepository.getAllByUserId(userId);
    }

    public List<Income> findAll() {
        return incomeRepository.findAll();
    }
}
