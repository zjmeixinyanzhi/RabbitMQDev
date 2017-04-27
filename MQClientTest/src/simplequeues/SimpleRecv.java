package simplequeues;

import com.rabbitmq.client.*;
import java.io.IOException;

public class SimpleRecv {
	
	 private final static String QUEUE_NAME = "hello";

	  public static void main(String[] argv) throws Exception {
		  String host="192.168.100.89";
	      String virtualHost = "/";
	      String username = "openstack";
	      String password = "123456";
		  
		
          ConnectionFactory factory = new ConnectionFactory();
          factory.setHost(host);
          factory.setVirtualHost(virtualHost);
          factory.setUsername(username);
  		  factory.setPassword(password);
  		  
          Connection connection = factory.newConnection();
          Channel channel = connection.createChannel();

	      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
          
	      Consumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	          String message = new String(body, "UTF-8");
	          System.out.println(" [x] Received '" + message + "'");
	        }
	      };
	      channel.basicConsume(QUEUE_NAME, true, consumer);
	  }

}
