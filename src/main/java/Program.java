import bot.ConsoleBot;
import bot.TelegramBot;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import repository.DbFactory;
<<<<<<< HEAD
import repository.IStateRepository;
import repository.RocksDbStateRepository;
import service.MessageHandler;
import service.StateHandler;
import service.api.DadataHandler;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import service.api.YandexWeatherHandler;
=======
import repository.RocksDbStateRepository;
import repository.StateRepository;
import service.MessageHandler;
import service.StateHandler;
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db

public class Program {

    public static void main(String[] args) throws Exception {

        DbFactory dbFactory = new DbFactory();
<<<<<<< HEAD
        try (RocksDB rocksDB = dbFactory.create("/home/azhukova/Desktop/rocksdb7")) {

            IStateRepository stateRepository = new RocksDbStateRepository(rocksDB);


            TestApiHandler testApiHandler = new TestApiHandler();


            WeatherHandler weatherHandler = new WeatherHandler(
                    new DadataHandler(),
                    new YandexWeatherHandler());


            StateHandler stateHandler = new StateHandler(
                    testApiHandler,
                    weatherHandler,
                    stateRepository);

            MessageHandler messageHandler = new MessageHandler(stateRepository,
                    stateHandler);


            if (System.getenv("TELEGRAM_BOT_TOKEN") != null) {
                new ConsoleBot(messageHandler);
            } else {
                new TelegramBot(
                        System.getenv("TELEGRAM_BOT_TOKEN"),
=======
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
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
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