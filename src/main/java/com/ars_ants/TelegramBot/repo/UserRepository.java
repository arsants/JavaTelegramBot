package com.ars_ants.TelegramBot.repo;

import com.ars_ants.TelegramBot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByChatId(long id);
}
