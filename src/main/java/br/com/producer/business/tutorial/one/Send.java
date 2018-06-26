package br.com.producer.business.tutorial.one;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import br.com.producer.business.IEnviarMensagem;
import br.com.producer.mocks.seeds.JsonExampleSeed;

public class Send implements IEnviarMensagem {
	private final static String QUEUE_NAME = "hello";
	
	public void enviarMensagem(String[] argv) throws IOException, TimeoutException {

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
