package com.ars_ants.TelegramBot.model;

import org.glassfish.grizzly.http.util.TimeStamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Income {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;
    private Float sum;
    private String category;
    private Timestamp timestamp;

    public Income() {
    }

    public Income(Long chatId, Float sum, String category, Timestamp timestamp) {
        this.user = new User(chatId);
        this.sum = sum;
        this.category = category;
        this.timestamp = timestamp;
    }
}
