package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Message;
import ar.fiuba.tecnicas.logger.out.OutputManager;

public class Logger {
	private static final String defaultProperties = "../resurces/default.properties"; 
	private Config config;
	private OutputManager outputManager;
    private LogProcessor logProcessor;
	
	public Logger(){
		this(new Config(defaultProperties));
	}
	
	public Logger(Config config){
		this.config = config;
		this.outputManager = new OutputManager(this.config);
        this.logProcessor = new LogProcessor(this.config);
	}
	
	public void log(Message message){
        if (!logProcessor.isFiltered(message)){
            logProcessor.processMenssage(message);
            this.outputManager.write(message);
        }//else ignore
	}
}
