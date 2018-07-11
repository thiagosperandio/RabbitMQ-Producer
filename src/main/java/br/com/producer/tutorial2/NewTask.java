package br.com.producer.tutorial2;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.json.JsonObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import br.com.producer.AppUtils;
import br.com.producer.mocks.seeds.JsonExampleSeed;
import br.com.producer.sender.IMessageSender;

public class NewTask implements IMessageSender {
	private final static String QUEUE_NAME = "task_queue";

	public void send() throws IOException, TimeoutException, IllegalArgumentException {
		JsonExampleSeed json = new JsonExampleSeed();
		send(json.getExemploJson());
	}
	
	public void send(JsonObject json) throws IOException, TimeoutException {

    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	boolean durable = true; // Fila deve ser persistida no servidor, mantendo-a caso ele páre.
    	channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
    	
    	channel.basicPublish("", QUEUE_NAME, 
    			MessageProperties.PERSISTENT_TEXT_PLAIN,  // Mensagem deve ser persistida no servidor, mantendo-a caso ele páre.
    			AppUtils.serialize(json));
    	System.out.println(" [x] Sent '" + json.toString() + "'");
    	    	
    	channel.close();
    	connection.close();
	}

}
