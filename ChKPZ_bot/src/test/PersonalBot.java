package test;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class PersonalBot {
	private long chat_id; // ������������
	private boolean step = false;// ��������� ��� �� ������ ��������
	private boolean register = false; // ��������� �����������
	private boolean x = true;// ���� �������� ����������� ����� ��
	// ������ ��� ����������� � ����
	private Mysqlcon pt = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "admin", "admin");
	Update update;// ������ ��� ���������� ���������
	// ������ ������� ��� ����������� ������������
	private IdeasForge IdeasForge = new IdeasForge(); // - ������� ����
	private SwitchAutoAdverts Switch = new SwitchAutoAdverts(); // ��������/�������
																// �� ����������
															
	private Commands comm = new Commands(); // ����� ������ ������ ����
											// ������������ ��� �����
	// �����������

	PersonalBot(long chati_id, Update update) {
		this.update = update;
		chat_id = chati_id;

	}

	// ���� ���������� ������������ ������� ����� ���������
	public void update(Update update) {
		this.update = update; // ������ ��� ������� ���������� ����� ���������
		Message messag = update.getMessage();
		String message_text = ""; // ��� ����� ����� ������������ � ������������
		if (x) {
			// �������� �� �����������
			if (this.registered(chat_id)) {
				register = true;

			} else {
				register = false;

			}
			x = false;// ��� �������� �������� ���������� ����� �������� �����
						// ��
		}
		if (step) { // ���� �������� ������ ���������� �� ������ � ������
					// �������� ����������
			if (messag != null && messag.hasText()) {// ���� ��������� ��
														// ������������
														// ���������
				if ((messag.getText().equals("paochkpz"))) // ����� ��������
															// ����� � ���������
															// :)
					try {// ���� ������ ����������, �� ��������� �������� � ����
						String query = "Insert into botmembers(chatid,registered,subscrube) values('" + chat_id
								+ "','1','1')";// ������
						pt.Other(query);// ��������� ������� ����������� ���
										// Update Delete Insert
						message_text = String.format("�� ������� ������������������ ��� ���������!"
								+ "%n��� ���� ����� �������� ������ ������ ������� ������� /help"
								+ "%n����� ������� ������� ���������� ������� ������� /stop");
						this.SendMessage(chat_id, message_text);// ����������
																// �����
																// ������������
						register = true;// ������ �� ���������������
						Switch.on(chat_id);// ������������� �� ����������
						step = false;// ������ ������ ������ � � ������ �������
					} catch (Exception ex) {// ��������� �������� ��� �����
											// ������
						message_text = String.format("������, ���������� �����");
						this.SendMessage(chat_id, message_text);
					}
			} else {// ���� ������� �� ����� ����������� � ��������� :)
				message_text = String.format("�������� ������.%n����� ������ ������ ��� ��� ������� �������: /start");
				this.SendMessage(chat_id, message_text);
				step = false;
			}
		} else {// ���� ������ �� ����������� ������
			if (messag != null && messag.hasText()) {
				if (messag.getText().equals("/start")) {// ���� �������� �������
														// /start (� ��������
														// ��� ���� ���
														// ��������� �������
														// ���� �������������)
					if (!register) {// ���� ������� �����������������
						message_text = String.format("�� �� ����������������� ��� ��������� �����������."
								+ "%n��� ��������� ������� � �������� ���� ���������� ������� ������");
						this.SendMessage(chat_id, message_text);
						step = true;

					}
				}
			}
			// ���� ������������ ���������������
			if (register) {
				IdeasForge.Osnova(update);// ������� �������� ������� ��
											// �������� ���� ��� ��� ��� �������
											// ���������� ��������� ��
											// ������������

				// ���� ������� ���������
				if (!IdeasForge.power) {
					new ShowAdverts().Update(update);// �������� �������
														// ���������� �� �������
					Switch.Update(update);// �����������/���������� ��
											// �������������� ��������
					comm.Update(update);// ���� �� ���� �� ������ �� �����������
										// ������� ����
				}

				// �������� ��������
				if (messag != null && messag.hasText()) {
					if ((messag.getText().equals("/stop"))) {// ���� ��������
																// ��������
																// (�������
																// �������� ����
																// �� �������
																// ���� ��� ��
																// �������)
						try {// ������� �� ����
							String query = "Delete From botmembers where chatid = '" + chat_id + "' ";
							pt.Other(query);
							message_text = String.format("�� ������� ������� ������� ����������!");
							this.SendMessage(chat_id, message_text);
							register = false;// ������� �� ����
						} catch (Exception e) {
							message_text = String.format("������, ���������� �����");
							this.SendMessage(chat_id, message_text);
						}
					}
				}
			} else {
				if (messag != null && messag.hasText()) {// ���� ������������
															// �����������������
					if (!messag.getText().equals("/start")) {// ����
																// ������������
																// ��� ���-��
																// ����� ����
																// �������
						message_text = String
								.format("����� ������������������ ��� ��������� �����������, ������� �������: /start");
						this.SendMessage(chat_id, message_text);
					}
				}
			}
		}
	}

	public boolean registered(long chat_id) {// ��������� ��������� �������� ��
												// �����������
		boolean reg = false;

		String query = "select registered from botmembers where chatid = '" + chat_id + "'";
		String[][] result = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "admin", "admin").Select(query, 1);
		if (result != null) {// ��� ��� ���� � ������� ���� ���� :)
			if (result[0][0].contains("1")) {
				reg = true;
			}
		}
		return reg;
	}

	public void SendMessage(long chat_id, String message_text) {
		new ChKPZ_bot().SendMessage(chat_id, message_text);// �� ������
															// ���������� ���
															// ������� ��
															// ��������� ������
	}

	public long getid() {// ���������� �������� ������������

		return chat_id;
	}

}
