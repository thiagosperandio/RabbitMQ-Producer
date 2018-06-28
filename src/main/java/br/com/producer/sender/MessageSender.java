package br.com.producer.sender;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import javax.json.JsonObject;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import br.com.producer.AppUtils;
import br.com.producer.sender.IMessageSender;

public class MessageSender implements IMessageSender {

	final static boolean DURABLE_QUEUE = true; // Fila deve ser persistida no servidor, mantendo-a caso ele páre.
	final static BasicProperties PERSISTENT_TEXT_PLAIN = MessageProperties.PERSISTENT_TEXT_PLAIN; // Mensagem deve ser persistida no servidor, mantendo-a caso ele páre.
	final static int PREFETCH_COUNT = 1; // Deve receber apenas X mensagem(ns) por vez para cada listeners.

	private String queue, host;
	Integer port;
	private JsonObject message;

	public MessageSender() {
		super();
	}

	public MessageSender(String queue, String host) {
		super();
		this.queue = queue;
		this.host = host;
	}

	public MessageSender(String queue, String host, Integer port) {
		super();
		this.queue = queue;
		this.host = host;
		this.port = port;
	}

	public MessageSender(String queue, String host, JsonObject message) {
		super();
		this.queue = queue;
		this.host = host;
		this.message = message;
	}

	public MessageSender(String queue, String host, Integer port, JsonObject message) {
		super();
		this.queue = queue;
		this.host = host;
		this.port = port;
		this.message = message;
	}

	public void send() 
			throws ConnectException, IOException, TimeoutException, IllegalArgumentException {
		send(null);
	}
	
	public void send(JsonObject json) 
			throws ConnectException, IOException, TimeoutException, IllegalArgumentException {
		if(json != null && !json.toString().isEmpty()) {
			setMessage(json);
		}
		if(getQueue() == null || getHost() == null || getMessage() == null || getMessage().isEmpty()) {
			throw new IllegalArgumentException("Incorrect or imcomplete data:"
					+ " fila: " + getQueue()
					+ ", host: " + getHost()
					+ ", port (optional): " + getPort()
					+ ", message (size): " + (getMessage() == null ? null : getMessage().toString().length())
					);
		}

    	ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(getHost());
		if (getPort() != null && getPort() > 0) {
			factory.setPort(getPort());	
		}
		
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	channel.queueDeclare(getQueue(), DURABLE_QUEUE, false, false, null);
    	
    	channel.basicPublish("", getQueue(), 
    			PERSISTENT_TEXT_PLAIN,
    			getMessage().toString().getBytes());
    	System.out.println((new Date()) + " [x] Sent: '" 
    			+ AppUtils.reduceStringAt(message.toString(), 100) + "(...)'"); // exibir x caracteres no console
    	    	
    	channel.close();
    	connection.close();
	}
	
	// getters and setters

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public JsonObject getMessage() {
		return message;
	}

	public void setMessage(JsonObject message) {
		this.message = message;
	}

}
