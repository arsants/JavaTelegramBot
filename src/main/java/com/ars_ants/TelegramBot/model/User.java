package com.ars_ants.TelegramBot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private Long chatId;
    private Integer stateId;
    private String password;
    private Boolean admin;
    private Boolean superUser = false;
    @OneToMany
    private List<Spend> spends;
    @OneToMany
    private List<Income> incomes;

    public User(){}
    public User(Long chatId) {
        this.chatId = chatId;
    }
    public User(Long chatId, Integer stateId) {
        this.chatId = chatId;
        this.stateId = stateId;
    }

    public User(Long chatId, Integer stateId, Boolean admin) {
        this.chatId = chatId;
        this.stateId = stateId;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public Boolean isSuperUser() {
        return superUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}