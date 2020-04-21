package com.ars_ants.TelegramBot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public enum BotState {

    Start {
        @Override
        public BotState handleInput(BotContext context) {
            return EnterPassword;
        }

        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Hello there");
        }
    },

    EnterPassword {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Enter password");
        }

        @Override
        public BotState handleInput(BotContext context) {
            if (context.getInput().length() < 6) {
                try {
                    sendMessage(context, "Your password should contains at least 6 character");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                return EnterPassword;
            } else {
                context.getUser().setPassword(context.getInput());
                return Usage;
            }
        }
    },

    Usage {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Welcome to Finance TgBot");
            sendMessage(context, "Usage: \n"
                    + "Select what you to do: \n"
                    + "type Income or Spend to select action");
        }

        @Override
        public BotState handleInput(BotContext context) {
            String input = context.getInput().toLowerCase();
            if (input.contains("income")) {
                return Income;
            } else if (input.contains("spend")) {
                return Spend;
            } else {
                try {
                    sendMessage(context, "You entered wrong action, try again.\n"
                            + "see usage down bellow");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                return Usage;
            }
        }
    },

    Spend {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Enter category and price to add your spend: \n"
                    + "Example: \n"
                    + "Category - price\n"
                    + "type \"exit\" to choose another action");
        }

        @Override
        public BotState handleInput(BotContext context) {
            if (context.getInput().toLowerCase().contains("exit")) {
                return Usage;
            } else if (context.getInput().contains("-")) {
                //TODO
                return Spend;
            } else {
                return Spend;
            }
        }
    },

    Income {
        @Override
        public void enter(BotContext context) throws TelegramApiException {
            sendMessage(context, "Enter category and price to add your spend: \n"
                    + "Example: \n"
                    + "Category - price\n"
                    + "type \"exit\" to choose another action");
        }

        @Override
        public BotState handleInput(BotContext context) {
            if (context.getInput().toLowerCase().contains("exit")) {
                return Usage;
            } else if (context.getInput().contains("-")) {
                return Income;
            } else {
                //TODO
                return Income;
            }
        }
    };

    BotState() {
    }

    public static BotState getInitialState() {
        return byId(0);
    }

    public static BotState byId(int id) {
        return BotState.values()[id];
    }

    protected void sendMessage(BotContext context, String text) throws TelegramApiException {
        SendMessage message = new SendMessage()
                .setChatId(context.getUser().getChatId())
                .setText(text);
        context.getBot().execute(message);
    }

    public abstract BotState handleInput(BotContext context);

    public abstract void enter(BotContext context) throws TelegramApiException;
}
