import bot.ConsoleBot;
import bot.TelegramBot;
import context.DialogContext;
import model.BotResponse;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import repository.DbFactory;
import repository.RocksDbStateRepository;
import repository.StateRepository;
import service.MessageHandler;
import service.StateHandler;

public class Program {

    public static void main(String[] args) throws Exception {

        DbFactory dbFactory = new DbFactory();
        try (RocksDB rocksDB = dbFactory.create("/home/azhukova/Desktop/rocksdb4")) {

            StateRepository stateRepository = new RocksDbStateRepository(rocksDB);
            DialogContext dialogContext = new DialogContext();

            //test bot
            ConsoleBot consoleBot = new ConsoleBot
              (new MessageHandler(
                    dialogContext,
                    stateRepository,
                    new StateHandler(dialogContext, stateRepository),
                    new BotResponse()));
            consoleBot.start();


            //telegram bot
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            TelegramBot bot = new TelegramBot(
                    stateRepository,
                    System.getenv("HELLO_BOT_TOKEN"),
                    new MessageHandler(
                            dialogContext,
                            stateRepository,
                            new StateHandler(dialogContext, stateRepository),
                            new BotResponse()
                    ));
            telegramBotsApi.registerBot(bot);
            while (true) {
                Thread.sleep(100);
            }
        } catch (TelegramApiRequestException | RocksDBException ex) {
            throw new RuntimeException(ex);
        }


    }

}