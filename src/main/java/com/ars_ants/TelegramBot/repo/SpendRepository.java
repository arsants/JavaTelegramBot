package com.ars_ants.TelegramBot.repo;

import com.ars_ants.TelegramBot.model.Spend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpendRepository extends JpaRepository<Spend, Long> {
    List<Spend> getAllByUserId(Long userId);
}
