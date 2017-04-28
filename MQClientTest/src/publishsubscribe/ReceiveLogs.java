package publishsubscribe;
import com.rabbitmq.client.*;
import java.io.IOException;

public class ReceiveLogs {
	private static final String EXCHANGE_NAME = "logs";

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
          
	      channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
	      String queueName = channel.queueDeclare().getQueue();
	      channel.queueBind(queueName, EXCHANGE_NAME, "");	      
          
	      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");          
	      Consumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope,
	                                   AMQP.BasicProperties properties, byte[] body) throws IOException {
	          String message = new String(body, "UTF-8");
	          System.out.println(" [x] Received '" + message + "'");
	        }
	      };
	      channel.basicConsume(queueName, true, consumer);
	  }

}
