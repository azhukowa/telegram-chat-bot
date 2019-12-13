package bot;

import model.messages.OutgoingMessage;
import model.messages.IncomingMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Bot {
    void onUpdate(IncomingMessage incomingMessage) throws TelegramApiException;
    void reply(OutgoingMessage outgoingMessage) throws TelegramApiException;

}
