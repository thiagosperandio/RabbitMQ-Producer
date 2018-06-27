package br.com.producer.testes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import br.com.producer.AppUtils;
import br.com.producer.mocks.seeds.JsonExampleSeed;
import br.com.producer.sender.IMessageSender;
import br.com.producer.sender.MessageSender;

public class EnviarMensagensFilaTest {


	private final static String QUEUE_NAME = "task_queue";
	private final static String HOST_NAME = "localhost";
	
	JsonExampleSeed message = new JsonExampleSeed();
	
	private IMessageSender sender1 = new MessageSender(QUEUE_NAME, HOST_NAME);
	private MessageSender sender2 = new MessageSender(QUEUE_NAME, HOST_NAME);
	
	public void enviarMensagens() throws IOException, TimeoutException, IllegalArgumentException {
		// teste 1
		sender1.send(message.getExemploJson()); 
		
		// teste 2
		sender2.setMessage(AppUtils.toJsonObject("Testando envio de string como JSON ... ! "));
		sender2.send(); 
	}	
}
