package ro.nymphis.service.email;

import ro.nymphis.model.order.Order;

public interface EmailService {
    void send(String to, String email, String subject);

	String getEmailBody(Order order);
}
