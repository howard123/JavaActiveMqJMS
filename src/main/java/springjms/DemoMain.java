package springjms;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"app-context.xml");

		JmsMessageSender jmsMessageSender = (JmsMessageSender) ctx
				.getBean("jmsMessageSender");

		jmsMessageSender.send("hello JMS");

		Queue queue = new ActiveMQQueue("AnotherDest");

		jmsMessageSender.send(queue, "hello Another Message");

		((ClassPathXmlApplicationContext) ctx).close();

	}
}
