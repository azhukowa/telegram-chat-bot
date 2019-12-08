package bot;

import context.DialogContext;
import model.ActionName;
import model.BotResponse;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repository.StateRepository;
import service.MessageHandler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class TelegramBot extends TelegramLongPollingBot {

    private final String token;

    private final StateRepository stateRepository;

    private final String myName;

    private final MessageHandler messageHandler;

    public TelegramBot(StateRepository stateRepository, String token, MessageHandler messageHandler) {
        this.token = token;
        this.messageHandler = messageHandler;
        this.stateRepository = stateRepository;
        try {
            User execute = execute(new GetMe());
            myName = execute.getUserName();
//            System.out.println("Started at @" + myName);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {

            Message message = update.getMessage();
            long chatId = message.getChatId();
            Integer msgId = message.getMessageId();
            String msgText = message.getText();

            if (message != null && message.hasText()) {

                BotResponse botResponse = messageHandler.process(chatId, msgText);

                SendMessage sendMessage = new SendMessage();
                execute(sendMessage
                        .setChatId(chatId)
                        .setText(botResponse.getReplyText())
                        .setReplyMarkup(buildReplyKeyboard(botResponse.getAvailableActions()))
                        .setReplyToMessageId(msgId));

            }

        } catch (TelegramApiException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    private ReplyKeyboard buildReplyKeyboard(List<ActionName> actionNames) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboard.clear();
        keyboardRow.clear();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        for (ActionName actionName : actionNames) {
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText(actionName.getActionName());
            keyboardRow.add(keyboardButton);
        }
        keyboard.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;

    }


    @Override
    public String getBotUsername() {
        return myName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
