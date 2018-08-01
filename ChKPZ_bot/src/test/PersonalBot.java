package test;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class PersonalBot {
	private long chat_id; // Пользователь
	private boolean step = false;// Проверяет идёт ли другая операция
	private boolean register = false; // Состояние регистрации
	private boolean x = true;// Ключ проверки регистрации через БД
	// Объект для подключения к базе
	private Mysqlcon pt = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "admin", "admin");
	Update update;// Объект для обновления сообщений
	// Список функций для конкретного пользователя
	private IdeasForge IdeasForge = new IdeasForge(); // - Кузница идей
	private SwitchAutoAdverts Switch = new SwitchAutoAdverts(); // Подписка/Отписка
																// на оповещение
															
	private Commands comm = new Commands(); // Выдаёт список команд если
											// пользователь ввёл фигню
	// Конструктор

	PersonalBot(long chati_id, Update update) {
		this.update = update;
		chat_id = chati_id;

	}

	// Если конкретный пользователь прислал новое сообщение
	public void update(Update update) {
		this.update = update; // Объект при котором появляется новое сообщение
		Message messag = update.getMessage();
		String message_text = ""; // Это потом будет отправляться к пользователю
		if (x) {
			// Проверка на авторизацию
			if (this.registered(chat_id)) {
				register = true;

			} else {
				register = false;

			}
			x = false;// Для экономии ресурсов достаточно одной проверки через
						// БД
		}
		if (step) { // Если проверка пароля выпоняется то доступ к другим
					// функциям невозможна
			if (messag != null && messag.hasText()) {// Если сообщение от
														// пользователя
														// текстовое
				if ((messag.getText().equals("paochkpz"))) // Самое уязвимое
															// место в программе
															// :)
					try {// Если пароль правильный, то добавляем человека в базу
						String query = "Insert into botmembers(chatid,registered,subscrube) values('" + chat_id
								+ "','1','1')";// Запрос
						pt.Other(query);// Процедура которая выполняется для
										// Update Delete Insert
						message_text = String.format("Вы успешно зарегистрировались как сотрудник!"
								+ "%nДля того чтобы получить список команд введите команду /help"
								+ "%nЧтобы удалить аккаунт сотрудника введите команду /stop");
						this.SendMessage(chat_id, message_text);// Отправляем
																// текст
																// пользователю
						register = true;// Теперь мы зарегестированы
						Switch.on(chat_id);// Подписываемся на оповещения
						step = false;// Теперь доступ открыт и к другим фукциям
					} catch (Exception ex) {// Сообщение выскочит при любой
											// ошибке
						message_text = String.format("Ошибка, попробуйте снова");
						this.SendMessage(chat_id, message_text);
					}
			} else {// Если человек не нашёл уязвимостей в телеграме :)
				message_text = String.format("Неверный пароль.%nЧтобы ввести пароль ещё раз введите команду: /start");
				this.SendMessage(chat_id, message_text);
				step = false;
			}
		} else {// Если сейчас не проверяется пароль
			if (messag != null && messag.hasText()) {
				if (messag.getText().equals("/start")) {// Если вводится команда
														// /start (а вводится
														// она сама при
														// первичном запуске
														// бота пользователем)
					if (!register) {// Если человек незарегестрирован
						message_text = String.format("Вы не зарегистрированны как сотрудник предприятия."
								+ "%nДля получения доступа к функциям бота пожайлуста введите пароль");
						this.SendMessage(chat_id, message_text);
						step = true;

					}
				}
			}
			// Если пользователь зарегистрирован
			if (register) {
				IdeasForge.Osnova(update);// Сначала проверям функцию на
											// отправку идей так как она требует
											// обновлений сообщений от
											// пользователя

				// Если функция выключена
				if (!IdeasForge.power) {
					new ShowAdverts().Update(update);// Показать текущие
														// объявления по запросу
					Switch.Update(update);// Подписаться/Отписаться на
											// автоматическую рассылку
					comm.Update(update);// Если ни одна из команд не затрагивает
										// функции выше
				}

				// Удаление аккаунта
				if (messag != null && messag.hasText()) {
					if ((messag.getText().equals("/stop"))) {// Если захотели
																// удалится
																// (простое
																// удаление бота
																// из списков
																// чата тут не
																// поможет)
						try {// Удаляем из базы
							String query = "Delete From botmembers where chatid = '" + chat_id + "' ";
							pt.Other(query);
							message_text = String.format("Вы успешно удалили аккаунт сотрудника!");
							this.SendMessage(chat_id, message_text);
							register = false;// Удаляем из чата
						} catch (Exception e) {
							message_text = String.format("Ошибка, попробуйте снова");
							this.SendMessage(chat_id, message_text);
						}
					}
				}
			} else {
				if (messag != null && messag.hasText()) {// Если пользователь
															// незарегистрирован
					if (!messag.getText().equals("/start")) {// Если
																// пользователь
																// ввёл что-то
																// кроме этой
																// команды
						message_text = String
								.format("Чтобы зарегестрироваться как сотрудник предприятия, введите команду: /start");
						this.SendMessage(chat_id, message_text);
					}
				}
			}
		}
	}

	public boolean registered(long chat_id) {// Процедура настоящей проверки на
												// регистрацию
		boolean reg = false;

		String query = "select registered from botmembers where chatid = '" + chat_id + "'";
		String[][] result = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "admin", "admin").Select(query, 1);
		if (result != null) {// Вот над этим я мучался пять дней :)
			if (result[0][0].contains("1")) {
				reg = true;
			}
		}
		return reg;
	}

	public void SendMessage(long chat_id, String message_text) {
		new ChKPZ_bot().SendMessage(chat_id, message_text);// Мы просто
															// заимствуем эту
															// функцию из
															// основного класса
	}

	public long getid() {// Возвращает текущего пользователя

		return chat_id;
	}

}
