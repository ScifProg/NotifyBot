package test;

import java.util.ArrayList;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

//��� ������� ��������� ������ ������ ��� �������� ������ ����� ��������� ����������������

//���� ����� �������� - ������������� ������� ������� ������ ��� ������������ ���� ����������� �����, ����� �� ������� ����������� ������ ��� ���
public class ChKPZ_bot extends TelegramLongPollingBot {
	// ��������� ������ ��� ��������� �������������;
	private ArrayList<PersonalBot> ListTh = new ArrayList<>();// ������ �������
	private ArrayList<Long> ListT = new ArrayList<>();// C����� �������������
	// ������� ����������
	AutoAdverts Thread1 = new AutoAdverts();// ����� �������������� ��������
	private boolean x = true;// ���� ��� ������� ������ �������������� ��������

	@Override
	public void onUpdateReceived(Update update) {// ����� �� ����������
													// ��������� ������������
													// ���������
		if (x) {
			Thread1.start();// ���� ����� ��������� ����� � ������������ ������
							// ���������� ������, �� ����� ��������� ������
			x = false;
		}

		long chat_id = update.getMessage().getChatId();// ����������
														// ������������
		
		if (ListT.contains(chat_id)) {// ����������, ���� � ������ ���� id
										// ������������, �� ��������� ��� �����
										// � ������ � ������� ���������� ������
										// ��� ����
			ListTh.get(ListT.indexOf(chat_id)).update(update);// ��������
																// ���������
																// ������ �
																// ����� �����
																// ������������,
																// ����� ��
																// ���������
																// �������� ��
																// ����
																// ������������
		} else {
			ListTh.add(new PersonalBot(chat_id, update));// ������ ���������
															// ������ ��� ������
															// �������������, ��
															// ������� ��� ����
															// �����������
			ListT.add(chat_id);
			ListTh.get(ListT.indexOf(chat_id)).update(update);
		}

	}

	public void SendMessage(long chat_id, String message_text) {// ��������
																// ����� ������
																// ����������
																// ��� ���������
																// ������

		SendMessage message = new SendMessage() // C������ ������ ���������
												// ���������
				.setChatId(chat_id).setText(message_text);// ��������� ���
															// �����������
		try {
			sendMessage(message); // ��������
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	// ��������� 2 ������� ����� ��� ���������� ����������� ����, ��� ���
	// ���������� �������� ��������� ������ ���� ����� ��������
	@Override
	public String getBotUsername() {
		// ��������� ��� ����
		return "ChKPZ_Bot";
	}

	@Override
	public String getBotToken() {
		// ��������� ����� ����
		return "your Token";
	}
}
