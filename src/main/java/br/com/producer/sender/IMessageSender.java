package br.com.producer.sender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.json.JsonObject;

public interface IMessageSender {

	public void send() throws IOException, TimeoutException, IllegalArgumentException;
	
	public void send(JsonObject json) throws IOException, TimeoutException, IllegalArgumentException;

}
