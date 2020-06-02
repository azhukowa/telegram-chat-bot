import bot.ConsoleBot;
import bot.TelegramBot;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import repository.DbFactory;
import repository.IStateRepository;
import repository.RocksDbStateRepository;
import service.MessageHandler;
import service.StateHandler;
import service.api.DadataHandler;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import service.api.YandexWeatherHandler;


public class Program {

    public static void main(String[] args) throws Exception {

        DbFactory dbFactory = new DbFactory();
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