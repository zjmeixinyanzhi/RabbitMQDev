package workqueues;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
	private static final String TASK_QUEUE_NAME = "task_queue";

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
          
	      channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
          
	      String message = getMessage(argv);
          
	      channel.basicPublish("", TASK_QUEUE_NAME,
	          MessageProperties.PERSISTENT_TEXT_PLAIN,
	          message.getBytes("UTF-8"));
	      System.out.println(" [x] Sent '" + message + "'");
          
	      channel.close();
	      connection.close();
	  }

	  private static String getMessage(String[] strings) {
	    if (strings.length < 1)
	      return "Hello World!";
	    return joinStrings(strings, " ");
	  }

	  private static String joinStrings(String[] strings, String delimiter) {
	    int length = strings.length;
	    if (length == 0) return "";
	    StringBuilder words = new StringBuilder(strings[0]);
	    for (int i = 1; i < length; i++) {
	      words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	  }
}
