package bot;

import model.Actions;
import model.messages.OutgoingMessage;
import model.messages.IncomingMessage;
import service.MessageHandler;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleBot implements Bot {

    private final MessageHandler messageHandler;


    public ConsoleBot(MessageHandler messageHandler) {

        this.messageHandler = messageHandler;

        Thread t = new Thread(() -> {
            while (true) {
                Scanner sc = new Scanner(System.in);
                Random messageId = new Random();
                reply(messageHandler.getCurrentStateMessage(4));
                while (true) {
                    String inputAction = sc.nextLine();
                    onUpdate(new IncomingMessage(4, inputAction, messageId.nextInt()));
                }
            }
        });
        t.start();
    }

    @Override
    public void onUpdate(IncomingMessage incomingMessage) {

        if (incomingMessage.getSelectedAction().equalsIgnoreCase("exit")) {
            System.exit(1);
            Thread.currentThread().interrupt();
        } else {
            reply(messageHandler.processMessage(incomingMessage));
        }
    }

    @Override
    public void reply(OutgoingMessage outgoingMessage) {
        StringBuilder text = new StringBuilder(outgoingMessage.getReplyText()).append("\n");
        text.append("Available actions:").append("\n");
        List<Actions> actions = outgoingMessage.getAvailableActions();
        if (actions != null) {
            for (Actions a : actions) {
                text.append("--> ").append(a.getActionName()).append("\n");
            }
        }

        System.out.println(text.toString());
    }

}