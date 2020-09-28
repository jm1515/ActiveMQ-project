package receiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyTopicReceiver {

	public static void main(String[] args) {
		try{

			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			ConnectionFactory factory = (ConnectionFactory) applicationContext.getBean("connectionFactory");

			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			Connection connection = factory.createConnection();

			connection.setClientID("3");

			try{
				connection.start();
			}catch(Exception e){
				System.err.println("NOT CONNECTED!!!");
			}

			// Open a session without transaction and acknowledge automatic
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			//Create a topic
			Topic topic = session.createTopic("topicConsumer2");

			// Create a receive
			MessageConsumer receiver = session.createDurableSubscriber(topic, "SUB1234");

			// Receive the message
			receiver.setMessageListener(new Listener());

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
