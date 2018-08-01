package test;

import java.util.ArrayList;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

//Для лучшего понимания работы первые два основных класса будут избыточно комментироваться

//Этот класс основной - сортировочная станция которая создаёт для пользователя свой собственный класс, чтобы их запросы выполнялись только для них
public class ChKPZ_bot extends TelegramLongPollingBot {
	// Отдельные классы для отдельных пользователей;
	private ArrayList<PersonalBot> ListTh = new ArrayList<>();// Список классов
	private ArrayList<Long> ListT = new ArrayList<>();// Cписок пользователей
	// Функция оповещения
	AutoAdverts Thread1 = new AutoAdverts();// Поток автоматической рассылки
	private boolean x = true;// Ключ для запуска потока автоматической рассылки

	@Override
	public void onUpdateReceived(Update update) {// Когда из телеграмма
													// поступает неопознанное
													// сообщение
		if (x) {
			Thread1.start();// Если будем запускать поток в конструкторе самого
							// потокового класса, то будут плодиться потоки
			x = false;
		}

		long chat_id = update.getMessage().getChatId();// Определяем
														// пользователя
		
		if (ListT.contains(chat_id)) {// Сортировка, если в списке есть id
										// пользователя, то соотносим его номер
										// в списке с номером отдельного класса
										// для него
			ListTh.get(ListT.indexOf(chat_id)).update(update);// Посылаем
																// сообщение
																// только в
																// класс этого
																// пользователя,
																// иначе бы
																// программа
																// отвечала не
																// тому
																// пользователю
		} else {
			ListTh.add(new PersonalBot(chat_id, update));// Создаёт отдельные
															// классы для разных
															// пользователей, их
															// индексы при этом
															// соотносятся
			ListT.add(chat_id);
			ListTh.get(ListT.indexOf(chat_id)).update(update);
		}

	}

	public void SendMessage(long chat_id, String message_text) {// Функцией
																// этого класса
																// пользуются
																// все остальные
																// классы

		SendMessage message = new SendMessage() // Cоздаем объект пересылки
												// сообщений
				.setChatId(chat_id).setText(message_text);// Наполняем его
															// необходимым
		try {
			sendMessage(message); // Посылаем
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	// Следующие 2 строчки нужны для правильной регистрации бота, без них
	// невозможна отправка сообщений потому этот класс основной
	@Override
	public String getBotUsername() {
		// Возращает имя бота
		return "ChKPZ_Bot";
	}

	@Override
	public String getBotToken() {
		// Возращает токен бота
		return "your Token";
	}
}
