package br.com.producer;

import java.util.concurrent.TimeoutException;

import br.com.producer.business.IEnviarMensagem;
import br.com.producer.business.tutorial.one.Send;
import br.com.producer.business.tutorial.two.NewTask;

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
