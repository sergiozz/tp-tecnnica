package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Message;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Vizcopa on 14/05/2014.
 */
public class LogProcessor {
    private Config config;
    private Level level;
    private int line;

    public LogProcessor(Config config) {
        this.config= config;
        this.level = config.getLevelFilter();
        this.line = 0;
    }

 	public Message processMessage(String userMessage, Level level,
			String filename, String methodName) {
		if (level.getValue() <= this.config.getLevelFilter().getValue()){
			Message message = new Message(userMessage, level, filename, methodName);
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
