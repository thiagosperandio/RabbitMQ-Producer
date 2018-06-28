package br.com.producer.sender;

import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import javax.json.JsonObject;

public interface IMessageSender {

	public void send() 
			throws ConnectException, IOException, TimeoutException, IllegalArgumentException;
	
	public void send(JsonObject json) 
			throws ConnectException, IOException, TimeoutException, IllegalArgumentException;

}
