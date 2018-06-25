package br.com.messageria.Producer;

import com.rabbitmq.client.ConnectionFactory;

import br.com.messageria.Producer.mocks.seeds.JsonExampleSeed;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.util.Date;
import java.util.concurrent.TimeoutException;

public class App 
{
	private final static String QUEUE_NAME = "hello";
	
    public static void main( String[] args ) 
    		throws java.io.IOException, TimeoutException
    {
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	//factory.setPort(32769);
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    	
    	// Envio de Texto comum:
    	String message = "Nova mensagem! Em " + (new Date());
    	channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    	System.out.println(" [x] Sent '" + message + "'");
    	
    	// Envio de Objeto JSON:
    	JsonExampleSeed message2 = new JsonExampleSeed();
    	channel.basicPublish("", QUEUE_NAME, null, message2.getExemploJson().toString().getBytes());
    	System.out.println(" [x] Sent '" + message2.getExemploJson().toString() + "'");
    	
    	
    	channel.close();
    	connection.close();
    }
}
