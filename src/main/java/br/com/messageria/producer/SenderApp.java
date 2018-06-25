package br.com.messageria.producer;

import java.util.concurrent.TimeoutException;

import br.com.messageria.producer.business.IEnviarMensagem;
import br.com.messageria.producer.business.TutorialOne;

public class SenderApp 
{
	
    public static void main( String[] args ) 
    		throws java.io.IOException, TimeoutException
    {
    	IEnviarMensagem enviar = new TutorialOne();
    	enviar.enviarMensagem();
    }
}
