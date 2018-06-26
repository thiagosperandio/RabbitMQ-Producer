package br.com.producer.business;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IEnviarMensagem {
	
	public void enviarMensagem(String[] args) throws IOException, TimeoutException;

}
