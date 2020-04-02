package com.ars_ants.TelegramBot.model;

import org.glassfish.grizzly.http.util.TimeStamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Income {
    @Id
    @GeneratedValue
    private Long id;

    private Long chatId;
    private Float sum;
    private String category;
    private Timestamp timestamp;

    public Income() {
    }

    public Income(Long chatId, Float sum, String category, Timestamp timestamp) {
        this.chatId = chatId;
        this.sum = sum;
        this.category = category;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() { return chatId; }

    public void setChatId(Long chatId) { this.chatId = chatId; }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
