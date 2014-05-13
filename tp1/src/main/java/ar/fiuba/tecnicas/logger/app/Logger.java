package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;

public class Logger {
	private static final String defaultProperties = "../resurces/default.properties"; 
	private Config config;
	
	public Logger(){
		this.config = new Config(defaultProperties);
	}
	
	public Logger(Config config){
		this.config = config;
	}
	
	public void log(String message){
		
	}
}
