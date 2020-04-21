package com.ars_ants.TelegramBot.service;

import com.ars_ants.TelegramBot.bot.BotContext;
import com.ars_ants.TelegramBot.bot.BotState;
import org.springframework.stereotype.Service;

@Service
public class StateHandlerService {
    public final UserService userService;
    public final SpendService spendService;
    public final IncomeService incomeService;

    public StateHandlerService(UserService userService, SpendService spendService, IncomeService incomeService) {
        this.userService = userService;
        this.spendService = spendService;
        this.incomeService = incomeService;
    }

    public void handleState(BotState state, BotContext context) {
        switch (state) {
            case Income:
                String[] str = context.getInput().replaceAll(" ", "").split("-");
                incomeService.create(context.getUser().getId(), str[0], Float.parseFloat(str[1]));
            case Spend:
                str = context.getInput().replaceAll(" ", "").split("-");
                spendService.create(context.getUser().getId(), str[0], Float.parseFloat(str[1]));
        }
    }
}
