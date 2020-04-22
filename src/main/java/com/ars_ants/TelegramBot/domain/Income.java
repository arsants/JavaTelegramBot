package com.ars_ants.TelegramBot.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "user_id")
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

    @Override
    public String toString() {
        String formattedDate = new SimpleDateFormat("MMM d HH:mm:ss").format(timestamp);
        return "Income: " +
                "sum: " + sum +
                ", category: " + category + '\'' +
                ", date: " + formattedDate;
    }
}
