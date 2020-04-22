package com.ars_ants.TelegramBot.repo;

import com.ars_ants.TelegramBot.domain.Spend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpendRepository extends JpaRepository<Spend, Long> {
    List<Spend> getAllByUserId(Long userId);
}
