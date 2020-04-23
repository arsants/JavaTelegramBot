package com.ars_ants.TelegramBot.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "spends")
public class Spend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    private Float sum;
    private String category;
    private Timestamp timestamp;

    public Spend() {
    }

    public Spend(Long userId, Float sum, String category, Timestamp timestamp) {
        this.user = new User(userId);
        this.sum = sum;
        this.category = category;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        String formattedDate = new SimpleDateFormat("MMM d HH:mm:ss").format(timestamp);
        return "Spend: " +
                "sum: " + sum +
                ", category: " + category +
                ", date: " + formattedDate;
    }
}
