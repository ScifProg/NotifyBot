package test;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class Commands {
	public void Update(Update update) {

		// We check if the update has a message and the message has text
		if (update.hasMessage()) {
			// Set variables
			String message_text = "";
			long chat_id = update.getMessage().getChatId();
			Message messag = update.getMessage();
			
			
			if (messag != null && messag.hasText()) {//Если сообщение не текстовое или отличается от тех комманд что написаны в условии то появляются подсказки
				if (!(messag.getText().equals("/adverts")) && !(messag.getText().equals("/idea"))
						&& !(messag.getText().equals("/stop") && !(messag.getText().equals("/start"))
								&& !(messag.getText().equals("/on")) && !(messag.getText().equals("/off")))) {
					message_text = "Список доступных команд: " + String.format("%n/adverts - Просмотреть объявления"
							+ "%n/idea - Предложить идею или решение по улучшению завода"
							+ "%n/on - Подписаться на автоматическую рассылку объявлений ПАО\"ЧКПЗ\""
							+ "%n/off - Отписаться от автоматической рассылки объявлений ПАО\"ЧКПЗ\""
							+ "%n/stop - Удалить профиль сотрудника");
					SendMessage(chat_id, message_text);
				}

			} else {
				message_text = "Список доступных команд: " + String.format("%n/adverts - Просмотреть объявления"
						+ "%n/idea - Предложить идею или решение по улучшению завода"
						+ "%n/on - Подписаться на автоматическую рассылку объявлений ПАО\"ЧКПЗ\""
						+ "%n/off - Отписаться от автоматической рассылки объявлений ПАО\"ЧКПЗ\""
						+ "%n/stop - Удалить профиль сотрудника");
				SendMessage(chat_id, message_text);
			}
		}
	}

	public void SendMessage(long chat_id, String message_text) {
		new ChKPZ_bot().SendMessage(chat_id, message_text);
	}

}