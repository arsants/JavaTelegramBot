package com.ars_ants.TelegramBot.domain;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long chatId;
    private Integer stateId;
    private String password;
    private Boolean admin;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Spend> spends;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Income> incomes;

    public User() {
    }

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
                '}';
    }

    public Set<Spend> getSpends() {
        return spends;
    }

    public void setSpends(Set<Spend> spends) {
        this.spends = spends;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }
}