package com.ars_ants.TelegramBot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


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
                    + "type Income or Spend to select action");
        }

        @Override
        public void handleInput(BotContext context) {
            if (context.getInput().toLowerCase().contains("income")) {
                next = Income;
            } else if (context.getInput().toLowerCase().contains("spend")) {
                next = Spend;
            } else {
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
            if (context.getInput().toLowerCase().contains("exit")) {
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
                if (!input.contains("regular") || !input.contains("other")) {
                    try {
                        sendMessage(context,"Wrong usage!");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    next = Income;
                } else {
                    next = IncomeAction;
                }
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
            return;
        }

        @Override
        public BotState nextState() {
            return Income;
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