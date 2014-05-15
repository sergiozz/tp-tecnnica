package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Message;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Vizcopa on 14/05/2014.
 */
public class LogProcessor {
    Config config;
    Level level;

    public LogProcessor(Config config) {
        this.config= config;
        this.level = config.getLevelFilter();
    }

 	public Message processMessage(String userMessage, Level level,
			String filename, String methodName) {
		if (level.getValue() <= this.config.getLevelFilter().getValue()){
			Message message = new Message(userMessage, level, filename, methodName);
			message.setDate(new Date());
			message.setThreadId(Thread.currentThread().getId());
			return message;
		}else{
			return null;
		}
	}

}
