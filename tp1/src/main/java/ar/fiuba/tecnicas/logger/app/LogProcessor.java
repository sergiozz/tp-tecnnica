package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Message;

import java.util.HashMap;

/**
 * Created by Vizcopa on 14/05/2014.
 */
public class LogProcessor {
    Config config;
    Integer level;

    public LogProcessor(Config config) {
        this.config= config;
        this.level = config.getLevelFilterOfConfig();
    }

    public boolean isFiltered(Message message) {
        if (config.getLevelFilter(message.getFilter()) <= level)
            return false;
        else
            return true;
    }

    public void processMenssage(Message message) {
         long threadId = Thread.currentThread().getId();
        //TODO
    }

}
