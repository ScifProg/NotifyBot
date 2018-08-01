package test;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
//ААА сложна!
public class IdeasForge {
	private int Step = 0;//Этапы выполнения функций
	// Работает ли функция
	public boolean power = false;//Для проверки основным классом используется функция или нет

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

	public int getStep() {//Получаем этап выполнения
		return Step;
	}

	public void Osnova(Update update) {//Главная процедура
		Thread myThready = new Thread(new Runnable() {//Создаем отдельный поток чтобы не мешать другим пользователям
			public void run() // Этот метод будет выполняться в побочном потоке
			{
				if (update.hasMessage()) {
					if (update.getMessage().hasText()) {
						// Устанавливаются локальные переменные
						String message_text = "";
						long chat_id = update.getMessage().getChatId();
						Message messag = update.getMessage();
						switch (Step) {//Определитель этапа выполнения
						case 0:
							NewIdea(chat_id, message_text, messag);
						
							break;
						case 1:

							if (Cancel(chat_id, message_text, messag)) {
								break;
							}
							Idea = GetIdea(chat_id, message_text, messag);//Берем данные
							if (Idea != null) {//Если пользователь заполнил информацию то идём на следующий этап
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
		myThready.start();//Запускаем поток при слежующим сообщении создастся новый
	}
//Второстепенные процедуры
	private void NewIdea(long chat_id, String message_text, Message messag) {//Процедура иницализации выполнения
		if (messag != null && messag.hasText()) {
			if (messag.getText().equals("/idea")) {
				message_text = String.format("Здравствуйте, благодарим вас за вашу помощь! "
						+ "%nЕсли хотите прекратить создание идеи, отправьте команду: %n /cancel");
				SendMessage(chat_id, message_text);
				message_text = String.format("Как назовём идею?");
				SendMessage(chat_id, message_text);
				Step++;
				power = true;
			}
		}
	}

	// Далее идут однотипные функции

	private String GetIdea(long chat_id, String message_text, Message messag) {
		String Idea = null;
		if (messag != null && messag.hasText()) {
			Idea = messag.getText();
			message_text = String
					.format("Отлично! Ваша идея называется " + "'" + Idea + "'" + "%n" + "Какая у вас фамилия?");
			SendMessage(chat_id, message_text);
			return Idea;
		} else {
			message_text = String.format("Простите, но что-то пошло не так. Введите ещё раз название вашей идеи.");
			SendMessage(chat_id, message_text);
			return Idea;
		}
	}

	private String GetName(long chat_id, String message_text, Message messag) {
		String Name = null;
		if (messag != null && messag.hasText() && NoNumbersTest(messag.getText()) && NoSignsTest(messag.getText())) {

			Name = messag.getText();
			message_text = String
					.format("Отлично! Ваше полное имя " + "'" + Name + "'" + "%n" + "Какое у вас отчество?");
			SendMessage(chat_id, message_text);
			return Name;
		} else {
			message_text = String.format("Простите, но что-то пошло не так. Введите ещё раз ваше полное имя.");
			SendMessage(chat_id, message_text);
			return Name;
		}
	}

	private String GetSurname(long chat_id, String message_text, Message messag) {
		String Surname = null;
		if (messag != null && messag.hasText() && NoNumbersTest(messag.getText()) && NoSignsTest(messag.getText())) {

			Surname = messag.getText();
			message_text = String
					.format("Отлично! Ваша фамилия " + "'" + Surname + "'" + ".%n" + "Какое у вас полное имя?");
			SendMessage(chat_id, message_text);
			return Surname;
		} else {
			message_text = String.format("Простите, но что-то пошло не так. Введите ещё раз свою фамилию.");
			SendMessage(chat_id, message_text);
			return Surname;
		}

	}

	private String GetOthestvo(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText()) {

			Othestvo = messag.getText();
			message_text = String.format("Отлично! Ваше отчество " + "'" + Othestvo + "'" + ".%n"
					+ "Напишите своё место работы на заводе цех или отдел.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format("Простите, но что-то пошло не так. Введите ещё раз своё отчество.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetJobPlace(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText() && NoNumbersTest(messag.getText()) && NoSignsTest(messag.getText())) {

			Othestvo = messag.getText();
			message_text = String.format("Отлично! Вы работаете тут: " + "'" + Othestvo + "'" + ".%n"
					+ "Пожалуйста, напишите номер вашего сотового телефона.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format("Простите, но что-то пошло не так. Введите ещё раз место своей работы.");
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
				message_text = String.format("Отлично! Ваш телефонный номер: " + "'" + Othestvo + "'" + ".%n"
						+ "Напишите свой адрес электронной почты");
				SendMessage(chat_id, message_text);
				return Othestvo;
			} else {
				message_text = String
						.format("Простите, но формат номера телефона неверный. Введите ещё раз свой телефонный номер.");
				SendMessage(chat_id, message_text);
				return Othestvo;
			}
		} catch (Exception ex) {
			message_text = String.format("Простите, но что-то пошло не так. Введите ещё раз свой телефонный номер.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetEmail(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		EmailValid em = new EmailValid();//Класс, проверяющий формат почты
		try {
			if (messag != null && messag.hasText() && em.validate(messag.getText())) {

				Othestvo = messag.getText();
				message_text = String.format("Отлично! Ваш электронный адрес" + "'" + Othestvo + "'" + ".%n"
						+ "Теперь опишите, что вы хотите, чтобы было сделано?");
				SendMessage(chat_id, message_text);
				return Othestvo;
			} else {
				message_text = String.format(
						"Простите, но формат почтового адреса не верный. Введите ещё раз свой адрес электронной почты.");
				SendMessage(chat_id, message_text);
				return Othestvo;
			}
		} catch (Exception ex) {
			message_text = String
					.format("Простите, но что-то пошло не так. Введите ещё раз свой адрес электронной почты.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetVision(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText()) {

			Othestvo = messag.getText();
			message_text = String
					.format("Отлично! Верим, что у вас хорошая идея. %n" + "Напишите, что должно получиться в итоге.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format("Простите, но что-то пошло не так. Опишите ещё раз свою идею.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetResult(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText()) {

			Othestvo = messag.getText();
			message_text = String.format("Отлично! Думаем, что это будет хороший результат. %n"
					+ "Напишите, какие ресурсы (материальные, трудовые и т.д.) нужно вложить в вашу идею.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format("Простите, но что-то пошло не так. Введите ещё раз планируемый результат.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	private String GetDebit(long chat_id, String message_text, Message messag) {
		String Othestvo = null;
		if (messag != null && messag.hasText()) {

			Othestvo = messag.getText();
			message_text = String.format("Отлично! Надеемся, что результат стоит того. %n"
					+ "Чтобы завершить создание идеи и добавить в её в \"Кузницу идей\", введите команду: %n/send.");
			SendMessage(chat_id, message_text);
			message_text = String.format("Чтобы отказаться от создания идеи, введите команду: %n/cancel.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		} else {
			message_text = String.format(
					"Прости, но что-то пошло не так.  Введите ещё раз, какие затраты потребуются для вашей идеи.");
			SendMessage(chat_id, message_text);
			return Othestvo;
		}
	}

	public void SendAll(long chat_id, String message_text, Message messag) {//Отправляем что получилось
		if (messag != null && messag.hasText()) {
			if (messag.getText().equals("/send")) {
				try {
					EmailSender es = new EmailSender("pushkinas@chkpz.ru", "kuznicajob@chkpz.ru", "smtp.chkpz.ru");//Класс для отправки на почту
					es.SendMessage(Idea, SurName, Name, Otchestvo, Job, PhoneNumber, Email, Vision, Result, Trata);
					message_text = String
							.format("Вы создали идею в проекте \"Кузница идей\". Мы обязательно рассмотрим Ваше предложение и свяжемся с вами ближайшее время. %n"
									+ "До встречи! %n" + "С уважением, %n" + "Коллектив проекта \"Кузница идей\"!");
					SendMessage(chat_id, message_text);
					Step = 0;
					power = false;

				} catch (Exception ex) {
					message_text = "Простите, но произошла ошибка при отправке письма.";
					SendMessage(chat_id, message_text);
				}
			} else {
				message_text = "Введите команду /send для отправки идеи.";
				SendMessage(chat_id, message_text);
			}
		} else {
			message_text = "Введите команду /send для отправки идеи.";
			SendMessage(chat_id, message_text);
		}
	}

	public void SendMessage(long chat_id, String message_text) {
		new ChKPZ_bot().SendMessage(chat_id, message_text);
	}

	public boolean NoNumbersTest(String test) {//Проверяем строку на отсутствие цифр

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

	public boolean NumbersTest(String test) {//Проверяем на отсутствие букв

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

	public boolean NoSignsTest(String test) {//Проверяем строку на отсутствие символов (Это можно было сделать короче с помощью регулярных выражений но мне лень)(PS: А это не лень :) )
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

	private boolean Cancel(long chat_id, String message_text, Message messag) {//Отказываемся от отправки
		if (messag != null && messag.hasText()) {
			if (messag.getText().equals("/cancel")) {
				Step = 0;

				message_text = "Создание идеи по улучшению завода прекращено.";
				SendMessage(chat_id, message_text);

				return true;
			}
		}
		return false;
	}

}