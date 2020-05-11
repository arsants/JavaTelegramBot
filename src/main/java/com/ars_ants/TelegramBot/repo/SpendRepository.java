package com.ars_ants.TelegramBot.repo;

import com.ars_ants.TelegramBot.domain.Spend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SpendRepository extends JpaRepository<Spend, Long> {
    Set<Spend> getAllByUserId(Long userId);
}
