package com.ars_ants.TelegramBot.repo;

import java.util.List;

import com.ars_ants.TelegramBot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByChatId(long id);
}
