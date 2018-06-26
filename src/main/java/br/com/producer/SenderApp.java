package br.com.producer;

import java.util.concurrent.TimeoutException;

import br.com.producer.business.IEnviarMensagem;
import br.com.producer.business.tutorial1.Send;
import br.com.producer.business.tutorial2.NewTask;
import br.com.producer.business.tutorial3.EmitLog;

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
    	case "3":
    		enviar = new EmitLog();
    		break;
    		
    	default:
    		enviar = new EmitLog();
    		break;
    	}
    	
    	System.out.println("Chamando " + (args[0] == null || args[0].isEmpty() ? "último tutorial" : "tutorial " + args[0]) + ". "); 
    	System.out.println("Classe chamada: " + enviar.getClass().getSimpleName());
    	
    	
    	enviar.enviarMensagem(args);
    }
}
