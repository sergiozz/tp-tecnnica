package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Level;
import ar.fiuba.tecnicas.logger.model.Message;

import java.util.Date;

/*
 * Responsabilities: Procesar una entrada de log del usuario,
 * agregandole linea, threadId, Fecha y entregando un objeto Mensaje.
 * Tambien se encarga de determinar si un mensaje debe ser logueado
 * de acuerdo a su nivel * 
 * 
 * */

public class LogProcessor {
    private Config config;
    private int line;

    public LogProcessor(Config config) {
        this.config= config;
        this.line = 0;
    }

 	public Message processMessage(String userMessage, Level level,
			String filename) {
		if (level.getValue() <= this.config.getLevel().getValue()){
            StackTraceElement[] ste = Thread.currentThread().getStackTrace();
            //el número 5 sale del conteo por invocación de métodos que se apilan
            String className = ste[5].getClassName();
            String methodName = ste[5].getMethodName();
            className = className.substring(className.lastIndexOf('.')+1);
			Message message = new Message(userMessage, level, filename, className+":"+methodName);
			message.setDate(new Date());
			message.setThreadId(Thread.currentThread().getId());
			message.setLine(this.line);
			this.line++;
			return message;
		}else{
			return null;
		}
	}

}
