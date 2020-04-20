package com.ars_ants.TelegramBot.service;


import com.ars_ants.TelegramBot.model.Spend;
import com.ars_ants.TelegramBot.repo.SpendRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SpendService {
    private final SpendRepository spendRepository;

    public SpendService(SpendRepository spendService) {
        this.spendRepository = spendService;
    }

    public Spend create(Long userId, float cost, String category) {
        return spendRepository.save(new Spend(userId, cost, category, new Timestamp(System.currentTimeMillis())));
    }

    public void delete(Long incomeId) {
        spendRepository.deleteById(incomeId);
    }

    public Spend findById(Long id) throws Exception {
        Optional<Spend> spend = spendRepository.findById(id);
        spend.orElseThrow(() -> new Exception("Wrong spend id"));
        return spend.get();
    }

    public List<Spend> getAllByUserId(Long userId) {
        return spendRepository.getAllByUserId(userId);
    }

    public List<Spend> findAll() {
        return spendRepository.findAll();
    }
}
