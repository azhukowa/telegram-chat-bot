package bot;

import model.OutgoingMessage;
import model.IncomingMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface IBot {
    void onUpdate(IncomingMessage incomingMessage) throws TelegramApiException;
    void reply(OutgoingMessage outgoingMessage) throws TelegramApiException;

}
