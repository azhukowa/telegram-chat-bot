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
import service.MessageHandler;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

<<<<<<< HEAD
public class ConsoleBot implements IBot {
=======
public class ConsoleBot implements Bot {
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db

    private final MessageHandler messageHandler;


    public ConsoleBot(MessageHandler messageHandler) {

        this.messageHandler = messageHandler;

        Thread t = new Thread(() -> {
<<<<<<< HEAD
            while (!Thread.currentThread().isInterrupted()) {
                Scanner sc = new Scanner(System.in);
                Random messageId = new Random();
                reply(messageHandler.getCurrentStateMessage(7));
                while (true) {
                    String inputAction = sc.nextLine();
                    onUpdate(new IncomingMessage(7, inputAction, messageId.nextInt()));
=======
            while (true) {
                Scanner sc = new Scanner(System.in);
                Random messageId = new Random();
                reply(messageHandler.getCurrentStateMessage(4));
                while (true) {
                    String inputAction = sc.nextLine();
                    onUpdate(new IncomingMessage(4, inputAction, messageId.nextInt()));
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
                }
            }
        });
        t.start();
    }

    @Override
    public void onUpdate(IncomingMessage incomingMessage) {

        if (incomingMessage.getSelectedAction().equalsIgnoreCase("exit")) {
<<<<<<< HEAD
            Thread.currentThread().interrupt();
            System.exit(1);
        } else {
                reply(messageHandler.processMessage(incomingMessage));
=======
            System.exit(1);
            Thread.currentThread().interrupt();
        } else {
            reply(messageHandler.processMessage(incomingMessage));
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
        }
    }

    @Override
    public void reply(OutgoingMessage outgoingMessage) {
        StringBuilder text = new StringBuilder(outgoingMessage.getReplyText()).append("\n");
        text.append("Available actions:").append("\n");
<<<<<<< HEAD
        List<Action> actions = outgoingMessage.getAvailableActions();
        if (actions != null) {
            for (Action a : actions) {
                text.append("--> ").append(a.getName()).append("\n");
=======
        List<Actions> actions = outgoingMessage.getAvailableActions();
        if (actions != null) {
            for (Actions a : actions) {
                text.append("--> ").append(a.getActionName()).append("\n");
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
            }
        }

        System.out.println(text.toString());
    }

}