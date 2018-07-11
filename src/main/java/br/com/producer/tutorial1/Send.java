package br.com.producer.tutorial1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.json.JsonObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import br.com.producer.mocks.seeds.JsonExampleSeed;
import br.com.producer.sender.IMessageSender;

public class Send implements IMessageSender {
	private final static String QUEUE_NAME = "hello";

	public void send() throws IOException, TimeoutException, IllegalArgumentException {
		JsonExampleSeed json = new JsonExampleSeed();
		send(json.getExemploJson());
	}
	
	public void send(JsonObject json) throws IOException, TimeoutException {

    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    	
    	channel.basicPublish("", QUEUE_NAME, null, json.toString().getBytes());
    	System.out.println(" [x] Sent '" + json + "'");
    	
    	
    	channel.close();
    	connection.close();
	}

}
