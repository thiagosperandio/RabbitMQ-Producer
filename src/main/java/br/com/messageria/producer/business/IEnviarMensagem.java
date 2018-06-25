package br.com.messageria.producer.business;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IEnviarMensagem {
	
	public void enviarMensagem() throws IOException, TimeoutException;

}
