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
            return EnterPhone;
        }
    },

    EnterPhone {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Enter phone");
        }

        @Override
        public void handleInput(BotContext context) {
            context.getUser().setPhone(context.getInput());
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
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Usage: "
                    + " "
                    + "Category - price"
                    + "\r\n");
        }

        @Override
        public BotState nextState() {
            return Start;
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
