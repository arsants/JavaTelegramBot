package com.ars_ants.TelegramBot.bot;

import com.ars_ants.TelegramBot.domain.Income;
import com.ars_ants.TelegramBot.domain.Spend;
import com.ars_ants.TelegramBot.domain.User;

import java.util.List;

public class BotContext {
    private final ChatBot bot;
    private final User user;
    private final String input;
    private List<Spend> spends;
    private List<Income> incomes;

    public static BotContext of(ChatBot bot, User user, String text, List<Spend> spends, List<Income> incomes) {
        return new BotContext(bot, user, text, spends, incomes);
    }

    private BotContext(ChatBot bot, User user, String input, List<Spend> spends, List<Income> incomes) {
        this.bot = bot;
        this.user = user;
        this.input = input;
        this.spends = spends;
        this.incomes = incomes;
    }

    public ChatBot getBot() {
        return bot;
    }

    public User getUser() {
        return user;
    }

    public String getInput() {
        return input;
    }

    public List<Spend> getSpends() {
        return spends;
    }


    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setSpends(List<Spend> spends) {
        this.spends = spends;
    }
}
