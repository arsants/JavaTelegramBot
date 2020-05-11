package com.ars_ants.TelegramBot.service;

import com.ars_ants.TelegramBot.domain.User;
import com.ars_ants.TelegramBot.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByChatId(long id) {
        return userRepository.findByChatId(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        user.setAdmin(userRepository.count() == 0);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
