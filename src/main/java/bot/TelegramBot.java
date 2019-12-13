package bot;

import model.Actions;
import model.messages.OutgoingMessage;
import model.messages.IncomingMessage;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import repository.StateRepository;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.MessageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TelegramBot extends TelegramLongPollingBot implements Bot {

    private final String token;

    private final String myName;

    private final MessageHandler messageHandler;


    static {
        ApiContextInitializer.init();
    }


    public TelegramBot(String token, MessageHandler messageHandler) {
        this.token = token;
        this.messageHandler = messageHandler;
        try {
            User execute = execute(new GetMe());
            myName = execute.getUserName();
            System.out.println("Started at @" + myName);
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        try {
            if (message != null && message.hasText()) {

                onUpdate(new IncomingMessage(message.getChatId(), message.getText(), message.getMessageId()));

            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void onUpdate(IncomingMessage incomingMessage) throws TelegramApiException {

        reply(messageHandler.processMessage(incomingMessage));

    }

    @Override
    public void reply(OutgoingMessage outgoingMessage) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        execute(sendMessage
                .setChatId(outgoingMessage.getChatId())
                .setText(outgoingMessage.getReplyText())
                .setReplyMarkup(buildReplyKeyboard(outgoingMessage.getAvailableActions()))
                .setReplyToMessageId(outgoingMessage.getMessageId()));
    }

    private static ReplyKeyboard buildReplyKeyboard(List<Actions> actions) {
        List<KeyboardButton> keyboardButtons = actions.stream()
                .map(a -> new KeyboardButton().setText(a.getActionName()))
                .collect(Collectors.toList());

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(keyboardButtons);

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
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
