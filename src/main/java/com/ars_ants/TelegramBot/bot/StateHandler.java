package com.ars_ants.TelegramBot.bot;

import com.ars_ants.TelegramBot.domain.Income;
import com.ars_ants.TelegramBot.domain.Spend;
import com.ars_ants.TelegramBot.service.IncomeService;
import com.ars_ants.TelegramBot.service.SpendService;
import com.ars_ants.TelegramBot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateHandler {
    public final UserService userService;
    public final SpendService spendService;
    public final IncomeService incomeService;

    public StateHandler(UserService userService, SpendService spendService, IncomeService incomeService) {
        this.userService = userService;
        this.spendService = spendService;
        this.incomeService = incomeService;
    }

    public void handleState(BotState state, BotContext context) {
        boolean isExit = context.getInput().contains("exit");
        switch (state) {
            case IncomeAction:
                if (isExit)
                    return;
                String[] str = context.getInput().replaceAll(" ", "").split("-");
                incomeService.create(context.getUser().getId(), str[0], Float.parseFloat(str[1]));
            case Spend:
                if (isExit)
                    return;
                str = context.getInput().replaceAll(" ", "").split("-");
                spendService.create(context.getUser().getId(), str[0], Float.parseFloat(str[1]));
            case SpendReport:
                if (isExit)
                    return;
                List<Spend> spends = spendService.getAllByUserId(context.getUser().getId());
                context.setSpends(spends);
            case IncomeReport:
                if (isExit)
                    return;
                List<Income> incomes = incomeService.getAllByUserId(context.getUser().getId());
                context.setIncomes(incomes);
        }
    }
}
