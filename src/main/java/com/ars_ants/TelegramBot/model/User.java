package com.ars_ants.TelegramBot.model;

import javax.persistence.*;
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Spend> spends;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Income> incomes;

    public User(){}
    public User(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", stateId=" + stateId +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", superUser=" + superUser +
                '}';
    }
}