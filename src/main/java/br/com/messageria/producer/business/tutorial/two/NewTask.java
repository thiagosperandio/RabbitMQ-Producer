package br.com.messageria.producer.business.tutorial.two;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import br.com.messageria.producer.business.IEnviarMensagem;

public class NewTask implements IEnviarMensagem {
	private final static String QUEUE_NAME = "task_queue";
	
	public void enviarMensagem(String[] argv) throws IOException, TimeoutException {

    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	boolean durable = true;
    	channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
    	
    	// Envio de Texto comum:
    	String message = getMessage(argv) + " - em " + (new Date());
    	channel.basicPublish("", QUEUE_NAME, 
    			MessageProperties.PERSISTENT_TEXT_PLAIN, 
    			message.getBytes());
    	System.out.println(" [x] Sent '" + message + "'");
    	
    	/*// Envio de Objeto JSON:
    	JsonExampleSeed message2 = new JsonExampleSeed();
    	channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message2.getExemploJson().toString().getBytes());
    	System.out.println(" [x] Sent '" + message2.getExemploJson().toString() + "'");*/
    	
    	channel.close();
    	connection.close();
	}
	
	private static String getMessage(String[] strings){
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
