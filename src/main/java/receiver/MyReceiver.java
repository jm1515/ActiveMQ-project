package receiver;

import javax.jms.*;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiver {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			QueueConnection connection = factory.createQueueConnection();

			// Open a session
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// start the connection
			connection.start();

			// Create a receive
			QueueReceiver receiver = session.createReceiver(queue);

			// Receive the message
			receiver.setMessageListener(new Listener());

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
