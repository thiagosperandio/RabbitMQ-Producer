package br.com.messageria.producer;

import java.util.concurrent.TimeoutException;

import br.com.messageria.producer.business.IEnviarMensagem;
import br.com.messageria.producer.business.tutorial.one.Send;
import br.com.messageria.producer.business.tutorial.two.NewTask;

public class SenderApp 
{
	
    public static void main( String[] args ) 
    		throws java.io.IOException, TimeoutException
    {
    	IEnviarMensagem enviar1 = new Send();
    	IEnviarMensagem enviar2 = new NewTask();
    	enviar2.enviarMensagem(args);
    }
}
