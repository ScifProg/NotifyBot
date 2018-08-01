package test;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
//Главный класс
public class Main {
    public static void main(String[] args) {
        //Инициализация API
        ApiContextInitializer.init();

        // Активация API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Регистрация бота
        try {
			botsApi.registerBot(new ChKPZ_bot());
		        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}