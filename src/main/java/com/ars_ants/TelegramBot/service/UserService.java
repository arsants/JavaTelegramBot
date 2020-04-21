package com.ars_ants.TelegramBot.service;

import com.ars_ants.TelegramBot.model.User;
import com.ars_ants.TelegramBot.repo.UserRepository;
import org.jetbrains.annotations.NotNull;
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

    public User addUser(@NotNull User user) {
        user.setAdmin(userRepository.count() == 0);
        System.out.println(user);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        System.out.println(user);
        return userRepository.save(user);
    }
}
