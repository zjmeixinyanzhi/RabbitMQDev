package simplequeues;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class SimpleSend {
	public static void main(String[] args) throws IOException, TimeoutException {
		String host="192.168.100.89";
    	String virtualHost = "/";
    	String username = "openstack";
    	String password = "123456";
    	
    	String QUEUE_NAME="hello";
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setVirtualHost(virtualHost);
        factory.setUsername(username);
		factory.setPassword(password);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		
		channel.close();
		connection.close();		
	}

}
