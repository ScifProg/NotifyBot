package test;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Java Program to send text mail using default SMTP server and without
 * authentication. You need mail.jar, smtp.jar and activation.jar to run this
 * program.
 * 
 * @author Javin Paul
 * 
 */
//�� google ��������� ��� �� ���������, ��� ��� ����������� �����������
public class EmailSender {
	String to;
	String from;
	String host;

	public EmailSender(String too, String fromo, String hosto) {
		to = too;
		from = fromo;
		host = hosto;

	}

	public void SendMessage(String Idea, String SurName, String Name, String Othestvo, String JobPlace,
			String PhoneNumber, String Email, String Vision, String Result, String Trata) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties); // default
																	// session
		try {
			MimeMessage message = new MimeMessage(session); // email message

			message.setFrom(new InternetAddress(from)); // setting header fields

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// subject line
			String line = "****************************";
			// actual mail body
			message.setText(String.format("�������� ����  %n" + line + "%n " + Idea + "%n %n" + "������� %n" + line
					+ "%n" + SurName + "%n %n " + "��� %n" + line + "%n" + Name + "%n %n " + "�������� %n" + line + "%n"
					+ Othestvo + "%n %n " + "����� ������, ���, ����� %n" + line + "%n" + JobPlace + "%n %n"
					+ "���������� ������� %n" + line + "%n" + PhoneNumber + "%n %n" + "����� ��. ����� %n" + line + "%n"
					+ Email + "%n %n" + "���� %n" + line + "%n" + Vision + "%n %n" + "����������� ��������� %n" + line
					+ "%n" + Result + "%n %n" + "������� %n" + line + "%n" + Trata + "%n %n" + "���� %n" + line
					+ "%n"));

			// Send message
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
