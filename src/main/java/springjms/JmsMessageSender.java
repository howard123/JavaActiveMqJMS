package springjms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageSender {

	@Autowired
	private JmsTemplate jmstemplate;

	public void send(final String text) {

		this.jmstemplate.send(new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				Message message = session.createTextMessage(text);

				message.setJMSReplyTo(new ActiveMQQueue("rec"));
				return message;
			}

		});
	}

	public void sendText(final String text) {
		this.jmstemplate.convertAndSend(text);
	}

	public void send(final Destination destination, final String text) {

		this.jmstemplate.send(destination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				Message message = session.createTextMessage(text);
				return message;
			}

		});
	}
}
