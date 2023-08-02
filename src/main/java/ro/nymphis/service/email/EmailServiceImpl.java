package ro.nymphis.service.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ro.nymphis.model.order.Order;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(EmailServiceImpl.class);

	private final JavaMailSender mailSender;

	@Override
	@Async
	public void send(String to, String email, String subject) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper =
					new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("do-not-reply@nymphis-technologies.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("failed to send email", e);
			throw new IllegalStateException("failed to send email");
		}
	}

	@Override
	public String getEmailBody(Order order) {
		return "Dear " + order.getUser().getFirstName() + " "
				+ order.getUser().getLastName() + ", thank you for your purchase. Your order number is "
				+ order.getOrderNumber();
	}

}

