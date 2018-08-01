package test;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class SwitchAutoAdverts {

	private boolean subscrube = false;
	private boolean x = true;

	public void setSubscrube(boolean x) {// Ключ выпонения
		subscrube = x;
	}

	public void Update(Update update) {
		long chat_id = update.getMessage().getChatId();
		Message messag = update.getMessage();
		String message_text = "";
		String query;
		try {
			if (x) {// Проверка на подписку
				query = "select subscrube from botmembers where chatid = '" + chat_id + "' ";
				String[][] result = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "admin", "admin").Select(query,
						1);
				if (result[0][0].contains("1")) {
					subscrube = true;
				} else {
					subscrube = false;
				}

			}
		} catch (Exception Ex) {

		}
		if (messag != null && messag.hasText()) {
			if (messag.getText().equals("/on")) {
				if (!subscrube) {

					this.on(chat_id);//Процедура подписки
				} else {
					message_text = String.format("Вы уже подписаны на автоматическую рассылку");
					SendMessage(chat_id, message_text);
				}
			}

			if (messag != null && messag.hasText()) {
				if (messag.getText().equals("/off")) {
					if (subscrube) {
						this.off(chat_id);//Процедура отписки
					} else {
						message_text = String.format("Вы ещё не подписаны на автоматическую рассылку");
						SendMessage(chat_id, message_text);
					}
				}
			}
		}
	}

	public void on(long chat_id) {//Подписка
		Mysqlcon pt = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "admin", "admin");
		String query = "UPDATE botmembers SET subscrube = '1' where '" + chat_id + "'";
		pt.Other(query);
		subscrube = true;
		String message_text = String.format("Вы подписались на автоматическую рассылку объявлений ПАО\"ЧКПЗ\""
				+ "%n Чтобы отписаться от рассылки, введите команду /off");
		SendMessage(chat_id, message_text);
	}

	public void off(long chat_id) {//Отписка
		Mysqlcon pt = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "admin", "admin");
		String query = "UPDATE botmembers SET subscrube = '0' where '" + chat_id + "'";
		pt.Other(query);
		subscrube = false;
		String message_text = String.format("Вы отписались от автоматической рассылки объявлений ПАО\"ЧКПЗ\""
				+ "%n Чтобы подписаться на рассылку, введите команду /on");
		SendMessage(chat_id, message_text);
	}

	public void SendMessage(long chat_id, String message_text) {
		new ChKPZ_bot().SendMessage(chat_id, message_text);
	}
}
