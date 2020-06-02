package bot;

import model.Action;
import model.IncomingMessage;
import model.OutgoingMessage;
import service.MessageHandler;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class ConsoleBot implements IBot {


    private final MessageHandler messageHandler;


    public ConsoleBot(MessageHandler messageHandler) {

        this.messageHandler = messageHandler;

        Thread t = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {
                Scanner sc = new Scanner(System.in);
                Random messageId = new Random();
                reply(messageHandler.getCurrentStateMessage(7));
                while (true) {
                    String inputAction = sc.nextLine();
                    onUpdate(new IncomingMessage(7, inputAction, messageId.nextInt()));
                }
            }
        });
        t.start();
    }

    @Override
    public void onUpdate(IncomingMessage incomingMessage) {

        if (incomingMessage.getSelectedAction().equalsIgnoreCase("exit")) {
            Thread.currentThread().interrupt();
            System.exit(1);
        } else {
                reply(messageHandler.processMessage(incomingMessage));
        }
    }

    @Override
    public void reply(OutgoingMessage outgoingMessage) {
        StringBuilder text = new StringBuilder(outgoingMessage.getReplyText()).append("\n");
        text.append("Available actions:").append("\n");
        List<Action> actions = outgoingMessage.getAvailableActions();
        if (actions != null) {
            for (Action a : actions) {
                text.append("--> ").append(a.getName()).append("\n");
            }
        }

        System.out.println(text.toString());
    }

}