package sender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyTopicSender {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			ConnectionFactory factory = (ConnectionFactory) applicationContext.getBean("connectionFactory");

			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			Connection connection = factory.createConnection();

			// Open a session without transaction and acknowledge automatic
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			//Create a topic
			Topic topic = session.createTopic("topicSender");

			// Start the connection
			connection.start();

			// Create a sender
			MessageProducer publisher = session.createProducer(topic);

			// Create a message
			TextMessage message = session.createTextMessage("Hello !!! I'm a topic sender !");

			// Send the message
			publisher.send(message);

			// Close the session
			session.close();

			// Close the connection
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
