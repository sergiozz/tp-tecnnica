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

 	public Message processMessage(String userMessage, Level level) {
		if (level.getValue() <= this.config.getLevel().getValue()){
            StackTraceElement[] ste = Thread.currentThread().getStackTrace();
            int index = getIndexStrakTrace(ste, level);

            String className = ste[index].getClassName();
            String methodName = ste[index].getMethodName();
            String fileName = ste[index].getFileName();
            className = className.substring(className.lastIndexOf('.')+1);

			Message message = new Message(userMessage, level, fileName, className+":"+methodName);
			message.setDate(new Date());
			message.setThreadId(Thread.currentThread().getId());
			message.setLine(this.line);
			this.line++;
			return message;
		}else{
			return null;
		}
	}

    //Se busca al metodo que llamo al logger, sabemos que fue uno anterior al del nombre level
    private int getIndexStrakTrace(StackTraceElement[] ste, Level level) {

        for (int index=1; index <= ste.length-1; index++){
            if ((ste[index].getMethodName()).contains(level.getName().toLowerCase())){
                return index+1;
            }
        }
        return 0;
    }

}
