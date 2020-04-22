package com.ars_ants.TelegramBot.repo;

import com.ars_ants.TelegramBot.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> getAllByUserId(Long userId);
}
