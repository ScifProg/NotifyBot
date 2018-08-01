package test;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
//��� ������!
public class IdeasForge {
	private int Step = 0;//����� ���������� �������
	// �������� �� �������
	public boolean power = false;//��� �������� �������� ������� ������������ ������� ��� ���

	private String Idea;
	private String Name;
	private String SurName;
	private String Otchestvo;
	private String Job;
	private String PhoneNumber;
	private String Email;
	private String Vision;
	private String Result;
	private String Trata;

	public int getStep() {//�������� ���� ����������
		return Step;
	}

	public void Osnova(Update update) {//������� ���������
		Thread myThready = new Thread(new Runnable() {//������� ��������� ����� ����� �� ������ ������ �������������
			public void run() // ���� ����� ����� ����������� � �������� ������
			{
				if (update.hasMessage()) {
					if (update.getMessage().hasText()) {
						// ��������������� ��������� ����������
						String message_text = "";
						long chat_id = update.getMessage().getChatId();
						Message messag = update.getMessage();
						switch (Step) {//������������ ����� ����������
						case 0:
							NewIdea(chat_id, message_text, messag);
						
							break;
						case 1:

							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Idea = GetIdea(chat_id, message_text, messag);//����� ������
							if (Idea != null) {//���� ������������ �������� ���������� �� ��� �� ��������� ����
								Step++;
							}
							break;
						case 3:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Name = GetName(chat_id, message_text, messag);
							if (Name != null) {
								Step++;
							}
							break;
						case 2:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							SurName = GetSurname(chat_id, message_text, messag);
							if (SurName != null) {
								Step++;
							}
							break;
						case 4:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Otchestvo = GetOthestvo(chat_id, message_text, messag);
							if (Otchestvo != null) {
								Step++;
							}
							break;
						case 5:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Job = GetJobPlace(chat_id, message_text, messag);
							if (Job != null) {
								Step++;
							}
							break;
						case 6:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							PhoneNumber = GetPhoneNumber(chat_id, message_text, messag);
							if (PhoneNumber != null) {
								Step++;
							}
							break;
						case 7:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Email = GetEmail(chat_id, message_text, messag);
							if (Email != null) {
								Step++;
							}
							break;
						case 8:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Vision = GetVision(chat_id, message_text, messag);
							if (Vision != null) {
								Step++;
							}
							break;
						case 9:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Result = GetResult(chat_id, message_text, messag);
							if (Result != null) {
								Step++;
							}
							break;
						case 10:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Trata = GetDebit(chat_id, message_text, messag);
							if (Trata != null) {
								Step++;
							}
							break;
						case 11:
							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							SendAll(chat_id, message_text, messag);
							break;
						}
					}
				}
			}
		});
		myThready.start();//��������� ����� ��� ��������� ��������� ��������� �����
	}
//�������������� ���������
	private void NewIdea(long chat_id, String message_text, Message messag) {//��������� ������������ ����������
		if (messag != null && messag.hasText()) {
			if (messag.getText().equals("/idea")) {
				message_text = String.format("������������, ���������� ��� �� ���� ������! "
						+ "%n���� ������ ���������� �������� ����, ��������� �������: %n /cancel");
				SendMessage(chat_id, message_text);
				message_text = String.format("��� ������ ����?");
				SendMessage(chat_id, message_text);
				Step++;
				power = true;
			}
		}
	}

	// ����� ���� ���������� �������

	private String GetIdea(long chat_id, String message_text, Message messag) {
		String Idea = null;
		if (messag != null && messag.hasText()) {
			Idea = messag.getText();
			message_text = String
					.format("�������! ���� ���� ���������� " + "'" + Idea + "'" + "%n" + "����� � ��� �������?");
			SendMessage(chat_id, message_text);
			return Idea;
		} else {
			message_text = String.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� �������� ����� ����.");
			SendMessage(chat_id, message_text);
			return Idea;
		}
	}

	private String GetName(long chat_id, String message_text, Message messag) {
		String Name = null;
		if (messag != null && messag.hasText() && NoNumbersTest(messag.getText()) && NoSignsTest(messag.getText())) {

			Name = messag.getText();
			message_text = String
					.format("�������! ���� ������ ��� " + "'" + Name + "'" + "%n" + "����� � ��� ��������?");
			SendMessage(chat_id, message_text);
			return Name;
		} else {
			message_text = String.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� ���� ������ ���.");
			SendMessage(chat_id, message_text);
			return Name;
		}
	}

	private String GetSurname(long chat_id, String message_text, Message messag) {
		String Surname = null;
		if (messag != null && messag.hasText() && NoNumbersTest(messag.getText()) && NoSignsTest(messag.getText())) {

			Surname = messag.getText();
			message_text = String
					.format("�������! ���� ������� " + "'" + Surname + "'" + ".%n" + "����� � ��� ������ ���?");
			SendMessage(chat_id, message_text);
			return Surname;
		} else {
			message_text = String.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� ���� �������.");
			SendMessage(chat_id, message_text);
			return Surname;
		}

	}

	private String GetOthestvo(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText()) {

			Othestvo = messag.getText();
			message_text = String.format("�������! ���� �������� " + "'" + Othestvo + "'" + ".%n"
					+ "�������� ��� ����� ������ �� ������ ��� ��� �����.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� ��� ��������.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetJobPlace(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText() && NoNumbersTest(messag.getText()) && NoSignsTest(messag.getText())) {

			Othestvo = messag.getText();
			message_text = String.format("�������! �� ��������� ���: " + "'" + Othestvo + "'" + ".%n"
					+ "����������, �������� ����� ������ �������� ��������.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� ����� ����� ������.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetPhoneNumber(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		try {
			if (messag != null && messag.hasText() && NumbersTest(messag.getText())
					&& (messag.getText().length() == 11)) {

				Othestvo = messag.getText();
				message_text = String.format("�������! ��� ���������� �����: " + "'" + Othestvo + "'" + ".%n"
						+ "�������� ���� ����� ����������� �����");
				SendMessage(chat_id, message_text);
				return Othestvo;
			} else {
				message_text = String
						.format("��������, �� ������ ������ �������� ��������. ������� ��� ��� ���� ���������� �����.");
				SendMessage(chat_id, message_text);
				return Othestvo;
			}
		} catch (Exception ex) {
			message_text = String.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� ���� ���������� �����.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetEmail(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		EmailValid em = new EmailValid();//�����, ����������� ������ �����
		try {
			if (messag != null && messag.hasText() && em.validate(messag.getText())) {

				Othestvo = messag.getText();
				message_text = String.format("�������! ��� ����������� �����" + "'" + Othestvo + "'" + ".%n"
						+ "������ �������, ��� �� ������, ����� ���� �������?");
				SendMessage(chat_id, message_text);
				return Othestvo;
			} else {
				message_text = String.format(
						"��������, �� ������ ��������� ������ �� ������. ������� ��� ��� ���� ����� ����������� �����.");
				SendMessage(chat_id, message_text);
				return Othestvo;
			}
		} catch (Exception ex) {
			message_text = String
					.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� ���� ����� ����������� �����.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetVision(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText()) {

			Othestvo = messag.getText();
			message_text = String
					.format("�������! �����, ��� � ��� ������� ����. %n" + "��������, ��� ������ ���������� � �����.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� ���� ����.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetResult(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText()) {

			Othestvo = messag.getText();
			message_text = String.format("�������! ������, ��� ��� ����� ������� ���������. %n"
					+ "��������, ����� ������� (������������, �������� � �.�.) ����� ������� � ���� ����.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format("��������, �� ���-�� ����� �� ���. ������� ��� ��� ����������� ���������.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetDebit(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText()) {

			Othestvo = messag.getText();
			message_text = String.format("�������! ��������, ��� ��������� ����� ����. %n"
					+ "����� ��������� �������� ���� � �������� � � � \"������� ����\", ������� �������: %n/send.");
			SendMessage(chat_id, message_text);
			message_text = String.format("����� ���������� �� �������� ����, ������� �������: %n/cancel.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format(
					"������, �� ���-�� ����� �� ���.  ������� ��� ���, ����� ������� ����������� ��� ����� ����.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	public void SendAll(long chat_id, String message_text, Message messag) {//���������� ��� ����������
		if (messag != null && messag.hasText()) {
			if (messag.getText().equals("/send")) {
				try {
					EmailSender es = new EmailSender("pushkinas@chkpz.ru", "kuznicajob@chkpz.ru", "smtp.chkpz.ru");//����� ��� �������� �� �����
					es.SendMessage(Idea, SurName, Name, Otchestvo, Job, PhoneNumber, Email, Vision, Result, Trata);
					message_text = String
							.format("�� ������� ���� � ������� \"������� ����\". �� ����������� ���������� ���� ����������� � �������� � ���� ��������� �����. %n"
									+ "�� �������! %n" + "� ���������, %n" + "��������� ������� \"������� ����\"!");
					SendMessage(chat_id, message_text);
					Step = 0;
					power = false;

				} catch (Exception ex) {
					message_text = "��������, �� ��������� ������ ��� �������� ������.";
					SendMessage(chat_id, message_text);
				}
			} else {
				message_text = "������� ������� /send ��� �������� ����.";
				SendMessage(chat_id, message_text);
			}
		} else {
			message_text = "������� ������� /send ��� �������� ����.";
			SendMessage(chat_id, message_text);
		}
	}

	public void SendMessage(long chat_id, String message_text) {
		new ChKPZ_bot().SendMessage(chat_id, message_text);
	}

	public boolean NoNumbersTest(String test) {//��������� ������ �� ���������� ����

		char c;
		for (int i = 0; i < test.length(); i++) {
			c = test.charAt(i);
			if ((c == '0') || (c == '1') || (c == '2') || (c == '3') || (c == '4') || (c == '5') || (c == '6')
					|| (c == '7') || (c == '8') || (c == '9')) {
				return false;
			}
		}
		return true;
	}

	public boolean NumbersTest(String test) {//��������� �� ���������� ����

		char c;
		for (int i = 0; i < test.length(); i++) {
			c = test.charAt(i);
			if (!((c == '0') || (c == '1') || (c == '2') || (c == '3') || (c == '4') || (c == '5') || (c == '6')
					|| (c == '7') || (c == '8') || (c == '9'))) {
				return false;
			}
		}
		return true;
	}

	public boolean NoSignsTest(String test) {//��������� ������ �� ���������� �������� (��� ����� ���� ������� ������ � ������� ���������� ��������� �� ��� ����)(PS: � ��� �� ���� :) )
		if (test.contains("!") || test.contains("#") || test.contains("$") || test.contains("%") || test.contains("&")
				|| test.contains("'") || test.contains("(") || test.contains(")") || test.contains("*")
				|| test.contains("+") || test.contains("-") || test.contains(".") || test.contains("/")
				|| test.contains(":") || test.contains(";") || test.contains("<") || test.contains("=")
				|| test.contains(">") || test.contains("?") || test.contains("@") || test.contains("[")
				|| test.contains("\\") || test.contains("]") || test.contains("^") || test.contains("_")
				|| test.contains("`") || test.contains("{") || test.contains("|") || test.contains("}")
				|| test.contains("~") || test.contains(",") || test.contains("\"")) {
			return false;
		}
		return true;
	}

	private boolean Cancel(long chat_id, String message_text, Message messag) {//������������ �� ��������
		if (messag != null && messag.hasText()) {
			if (messag.getText().equals("/cancel")) {
				Step = 0;

				message_text = "�������� ���� �� ��������� ������ ����������.";
				SendMessage(chat_id, message_text);

				return true;
			}
		}
		return false;
	}

}