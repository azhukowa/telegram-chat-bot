import bot.ConsoleBot;
import bot.TelegramBot;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import repository.DbFactory;
import repository.RocksDbStateRepository;
import repository.StateRepository;
import service.MessageHandler;
import service.StateHandler;

public class Program {

    public static void main(String[] args) throws Exception {

        DbFactory dbFactory = new DbFactory();
        try (RocksDB rocksDB = dbFactory.create("/home/azhukova/Desktop/rocksdb6")) {

            StateRepository stateRepository = new RocksDbStateRepository(rocksDB);

            StateHandler stateHandler = new StateHandler(stateRepository);

            MessageHandler messageHandler = new MessageHandler(
                    stateRepository,
                    stateHandler);

            if (System.getenv("HELLO_BOT_TOKEN") != null) {
                ConsoleBot consoleBot = new ConsoleBot(messageHandler);
            } else {
                new TelegramBot(
                        System.getenv("HELLO_BOT_TOKEN"),
                        messageHandler);
            }

            while (true) {
                Thread.sleep(100);
            }
        } catch (RocksDBException ex) {
            throw new RuntimeException(ex);
        }
    }

}