package bot;

import context.DialogContext;
import model.ActionName;
import model.BotResponse;
import repository.StateRepository;
import service.MessageHandler;
import service.StateHandler;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class ConsoleBot {

    private final MessageHandler messageHandler;
    private BotResponse botResponse;
    private StringBuilder text;

    public ConsoleBot(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void start() throws UnsupportedEncodingException {

        text = new StringBuilder("Welcome to chat. Type 'exit' for exit. \n");
        messageHandler.getStateHandler().switchContextState(1);
        text.append(messageHandler.getDialogContext().returnReplyText()).append("\n");
        text.append("Available actions:").append("\n");
        List<ActionName> actionNames = messageHandler.getDialogContext().returnActionNames();
        if (actionNames != null) {
            for (ActionName a : actionNames) {
                text.append("--> ").append(a.getActionName()).append("\n");
            }
        }

        System.out.println(text.toString());


        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            System.out.println("User: " + line);
            if (line.equalsIgnoreCase("exit")) {
                System.exit(1);
            } else {
                botResponse = messageHandler.process(1, line);
                text = new StringBuilder(botResponse.getReplyText()).append("\n");
                text.append("Available actions:").append("\n");
                actionNames = botResponse.getAvailableActions();
                if (actionNames != null) {
                    for (ActionName a : actionNames) {
                        text.append("--> ").append(a.getActionName()).append("\n");
                    }
                }

                System.out.println(text.toString());
            }
        }
    }

}