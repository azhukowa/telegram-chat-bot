package bot;

<<<<<<< HEAD
import model.Action;
import model.OutgoingMessage;
import model.IncomingMessage;
=======
import model.Actions;
import model.messages.OutgoingMessage;
import model.messages.IncomingMessage;
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
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

<<<<<<< HEAD
import java.lang.reflect.InvocationTargetException;
=======
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


<<<<<<< HEAD
public class TelegramBot extends TelegramLongPollingBot implements IBot {
=======
public class TelegramBot extends TelegramLongPollingBot implements Bot {
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db

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
<<<<<<< HEAD
            //System.out.println("Started at @" + myName);
=======
            System.out.println("Started at @" + myName);
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
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
<<<<<<< HEAD
            reply(messageHandler.processMessage(incomingMessage));
=======

        reply(messageHandler.processMessage(incomingMessage));

>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
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

<<<<<<< HEAD
    private static ReplyKeyboard buildReplyKeyboard(List<Action> actions) {
        List<KeyboardButton> keyboardButtons = actions.stream()
                .map(a -> new KeyboardButton().setText(a.getName()))
=======
    private static ReplyKeyboard buildReplyKeyboard(List<Actions> actions) {
        List<KeyboardButton> keyboardButtons = actions.stream()
                .map(a -> new KeyboardButton().setText(a.getActionName()))
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
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
