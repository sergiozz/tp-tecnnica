package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.out.OutputManager;

public class Logger {
	private static final String defaultProperties = "../resurces/default.properties"; 
	private Config config;
	private OutputManager outputManager;
	
	public Logger(){
		this(new Config(defaultProperties));
		
	}
	
	public Logger(Config config){
		this.config = config;
		this.outputManager = new OutputManager(this.config);
	}
	
	public void log(String message){
		
	}
}
