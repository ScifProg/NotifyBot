package test;


import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;



public class ShowAdverts {

	public void Update(Update update) {

		String query;
		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			// Устанавливаем переменные
			long chat_id = update.getMessage().getChatId();
			Message messag = update.getMessage();
			String message_text = "";

		

				if (messag != null && messag.hasText()) {//Показываем объявления по заросу

					if (messag.getText().equals("/adverts")) {

					Mysqlcon pt = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "admin", "admin");
						query = "select Advert,BeginDate,EndDate,SLEEP(0) from Adverts";
						if (pt.Select(query, 3) != null) {
							String[][] result = pt.Select(query, 3);
							int b = 0;
							
							
							
							while (b < result.length) {
								result[b][1].substring(0, result[b][1].length()-2);//Это попытка убрать лишние нули
								result[b][2].substring(0, result[b][2].length()-2);
								message_text = message_text + String.format(result[b][1] + "%n" + '"' + result[b][0]
										+ '"' + "%n" + "Актуально до: " + result[b][2] + "%n" + "%n");
								b++;
							}
							query = "Delete From adverts where EndDate < NOW() ";
							pt.Other(query);
							if(message_text != ""){
							SendMessage(chat_id, message_text);
						}
						} else {
							SendMessage(chat_id, "Актуальных объявлений пока нет");
						}
			
					}
				}
		}
	}
	public void SendMessage(long chat_id, String message_text) {
		new ChKPZ_bot().SendMessage(chat_id, message_text);	
	}

}
