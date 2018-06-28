package br.com.producer;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import br.com.producer.sender.IMessageSender;
import br.com.producer.testes.EnviarMensagensFilaTest;
import br.com.producer.tutorial1.Send;
import br.com.producer.tutorial2.NewTask;
import br.com.producer.tutorial3.EmitLog;

public class SenderApp {
	
    public static void main(String[] argv) {
    	IMessageSender enviar = null;
    	String opt = argv == null || argv.length == 0 ? "" : argv[0]; 
    	try {
			switch(opt) {
			case "1":
				enviar = new Send();
				sendString(enviar, argv);
				break;
			case "2":
				enviar = new NewTask();
				sendString(enviar, argv);
				break;
			case "3":
				enviar = new EmitLog();
				sendString(enviar, argv);
				break;
				
			default:
				EnviarMensagensFilaTest senderTeste = new EnviarMensagensFilaTest();
				senderTeste.enviarMensagens();
				break;
			}
		} catch (ConnectException e) {
			e.printStackTrace();
			System.out.println("Erro de ConnectException: " + e.getMessage() + " - " + e.getCause());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Erro de IllegalArgumentException: " + e.getMessage() + " - " + e.getCause());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro de IOException: " + e.getMessage() + " - " + e.getCause());
		} catch (TimeoutException e) {
			e.printStackTrace();
			System.out.println("Erro de TimeoutException: " + e.getMessage() + " - " + e.getCause());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro de Exception: " + e.getMessage() + " - " + e.getCause());
		}
    	
    	System.out.println("Chamada ao programa " + (opt.isEmpty() ? "default" : "tutorial " + opt) + ". ");
    }
    
    static void sendString(IMessageSender enviar, String[] argv) 
			throws ConnectException, IOException, TimeoutException, IllegalArgumentException {
    	// envio tipo 1
    	enviar.send(AppUtils.toJsonObject(AppUtils.getStringFromArray(argv) + " - em " + (new Date())));
    	
    	 // envio tipo 2
		enviar.send();
    }
}
