package br.com.producer;

import java.util.concurrent.TimeoutException;

import br.com.producer.business.IEnviarMensagem;
import br.com.producer.business.tutorial1.*;
import br.com.producer.business.tutorial2.*;

public class SenderApp {
	
    public static void main(String[] args) 
    		throws java.io.IOException, TimeoutException {
    	IEnviarMensagem enviar = null;
    	switch(args[0]) {
    	case "1":
    		enviar = new Send();
    		break;
    	case "2":
    		enviar = new NewTask();
    		break;
    	default:
    		enviar = new NewTask();
    		break;
    	}
    	enviar.enviarMensagem(args);
    }
}
