package br.com.producer.business.tutorial3;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import br.com.producer.business.IEnviarMensagem;

public class EmitLog implements IEnviarMensagem {
	private static final String EXCHANGE_NAME = "logs";
	
	public void enviarMensagem(String[] argv) throws IOException, TimeoutException {

    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    	
    	//JsonExampleSeed message = new JsonExampleSeed();
    	String message = getMessage(argv) + " - em " + (new Date());
    	
    	channel.basicPublish(EXCHANGE_NAME, "", 
    			null, 
    			message.getBytes());
    	System.out.println(" [x] Sent '" + message + "'");
    	
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
