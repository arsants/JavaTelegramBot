package com.ars_ants.TelegramBot.bot;

import com.ars_ants.TelegramBot.domain.Income;
import com.ars_ants.TelegramBot.domain.Spend;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Set;


public enum BotState {

    Start {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Hello there");
        }

        @Override
        public BotState nextState() {
            return EnterPassword;
        }
    },

    EnterPassword {
        private BotState next;

        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Enter password");
        }

        @Override
        public void handleInput(BotContext context) {
            if (context.getInput().length() < 6) {
                try {
                    sendMessage(context, "Your password should contains at least 6 character");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                next = EnterPassword;
            } else {
                context.getUser().setPassword(context.getInput());
                next = Approved;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    Approved(false) {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Welcome to Finance TgBot");
        }

        @Override
        public BotState nextState() {
            return Usage;
        }
    },

    Usage {
        private BotState next;

        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Usage: \n"
                    + "Select what you to do: \n"
                    + "type Income/Spend/Report to select action");
        }

        @Override
        public void handleInput(BotContext context) {
            String input = context.getInput().toLowerCase();
            switch (input) {
                case "income":
                    next = Income;
                    break;
                case "spend":
                    next = Spend;
                    break;
                case "report":
                    next = Report;
                    break;
                default:
                    try {
                        sendMessage(context, "You entered wrong action, try again.\n"
                                + "see usage down bellow");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    next = Usage;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    Spend {
        private BotState next;

        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Enter category and price to add your spend: \n"
                    + "Example: \n"
                    + "Category - price\n"
                    + "type \"exit\" to choose another action");
        }

        @Override
        public void handleInput(BotContext context) {
            String input = context.getInput().toLowerCase();
            if (input.contains("exit")) {
                next = Usage;
            } else if (context.getInput().contains("-")) {
                next = Spend;
            } else {
                next = Spend;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    Income {
        private BotState next;

        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Enter category and price to add your spend: \n"
                    + "Select category:\n"
                    + "Regular or other\n"
                    + "Example: \n"
                    + "Category - price\n"
                    + "type \"exit\" to choose another action");
        }

        @Override
        public void handleInput(BotContext context) {
            String input = context.getInput().toLowerCase();
            if (input.contains("exit")) {
                next = Usage;
            } else if (input.contains("-")) {
                if (input.contains("regular") || input.contains("other")) {
                    next = IncomeAction;
                } else {
                    try {
                        sendMessage(context, "Wrong usage!");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    next = Income;
                }
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    Report {
        private BotState next;

        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Select type of report\n" +
                    "Type: Spend/Income to select" +
                    "type \"exit\" to choose another action");
        }

        @Override
        public void handleInput(BotContext context) {
            String input = context.getInput().toLowerCase();
            switch (input) {
                case "income":
                    next = IncomeReport;
                    break;
                case "spend":
                    next = SpendReport;
                    break;
                default:
                    try {
                        sendMessage(context, "You entered wrong action, try again.");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    next = Usage;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    IncomeAction(false) {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
        }

        @Override
        public BotState nextState() {
            return Income;
        }
    },

    SpendReport(false) {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            Set<Spend> spends = context.getUser().getSpends();
            StringBuilder sb = new StringBuilder();
            if (spends == null) {
                sb.append("You have no added any spends yet");
            } else {
                for (var e : spends) {
                    sb.append(e.toString());
                    sb.append("\n");
                }
            }
            sendMessage(context, sb + "\n");
        }

        @Override
        public BotState nextState() {
            return Usage;
        }
    },

    IncomeReport(false) {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            Set<Income> incomes = context.getUser().getIncomes();
            StringBuilder sb = new StringBuilder();
            if (incomes == null) {
                sb.append("You have no added any incomes yet");
            } else {
                for (var e : incomes) {
                    sb.append(e.toString());
                    sb.append("\n");
                }
            }

            sendMessage(context, sb + "\n");
        }

        @Override
        public BotState nextState() {
            return Usage;
        }
    };


    private static BotState[] states;
    private final boolean inputNeeded;

    BotState() {
        this.inputNeeded = true;
    }

    BotState(boolean inputNeeded) {
        this.inputNeeded = inputNeeded;

    }

    public static BotState getInitialState() {
        return byId(0);
    }

    public static BotState byId(int id) {
        if (states == null) {
            states = BotState.values();
        }
        return states[id];
    }

    protected void sendMessage(BotContext context, String text) throws TelegramApiException {
        SendMessage message = new SendMessage()
                .setChatId(context.getUser().getChatId())
                .setText(text);
        context.getBot().execute(message);
    }

    public boolean isInputNeeded() {
        return inputNeeded;
    }

    public void handleInput(BotContext context) {
    }

    public abstract void enter(BotContext context) throws TelegramApiException;

    public abstract BotState nextState();
}