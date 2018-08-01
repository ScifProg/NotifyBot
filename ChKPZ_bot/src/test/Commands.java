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
			
			
			if (messag != null && messag.hasText()) {//���� ��������� �� ��������� ��� ���������� �� ��� ������� ��� �������� � ������� �� ���������� ���������
				if (!(messag.getText().equals("/adverts")) && !(messag.getText().equals("/idea"))
						&& !(messag.getText().equals("/stop") && !(messag.getText().equals("/start"))
								&& !(messag.getText().equals("/on")) && !(messag.getText().equals("/off")))) {
					message_text = "������ ��������� ������: " + String.format("%n/adverts - ����������� ����������"
							+ "%n/idea - ���������� ���� ��� ������� �� ��������� ������"
							+ "%n/on - ����������� �� �������������� �������� ���������� ���\"����\""
							+ "%n/off - ���������� �� �������������� �������� ���������� ���\"����\""
							+ "%n/stop - ������� ������� ����������");
					SendMessage(chat_id, message_text);
				}

			} else {
				message_text = "������ ��������� ������: " + String.format("%n/adverts - ����������� ����������"
						+ "%n/idea - ���������� ���� ��� ������� �� ��������� ������"
						+ "%n/on - ����������� �� �������������� �������� ���������� ���\"����\""
						+ "%n/off - ���������� �� �������������� �������� ���������� ���\"����\""
						+ "%n/stop - ������� ������� ����������");
				SendMessage(chat_id, message_text);
			}
		}
	}

	public void SendMessage(long chat_id, String message_text) {
		new ChKPZ_bot().SendMessage(chat_id, message_text);
	}

}