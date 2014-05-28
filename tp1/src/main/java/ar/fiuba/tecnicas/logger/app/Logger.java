package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Level;
import ar.fiuba.tecnicas.logger.model.Message;
import ar.fiuba.tecnicas.logger.out.OutputManager;


/*
 * Responsabilities: Clase que presenta los servicios de logueo al usuario
 * 
 * 
 * */

public class Logger {
    private static final String defaultProperties = "../resurces/default.properties";
    public static final String STANDARD_OUT_TXT = "StandardOut.txt";
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

    public void debug(String userMessage){log(userMessage, Level.DEBUG);}

    public void info(String userMessage){log(userMessage, Level.INFO);}

    public void warn(String userMessage){log(userMessage, Level.WARN);}

    public void error(String userMessage){log(userMessage, Level.ERROR);}

    public void fatal(String userMessage){log(userMessage, Level.FATAL);}

    public void off(String userMessage){log(userMessage, Level.OFF);}

	private void log(String userMessage){
		this.log(userMessage, Level.DEBUG);
	}

    private void log(String userMessage, Level level){
		this.log(userMessage, level, STANDARD_OUT_TXT);
	}

    private void log(String userMessage, Level level, String filename){
		Message message = logProcessor.processMessage(userMessage, level, filename);
		if (message != null){
			this.outputManager.write(message);        
		}
	}
	
	public void close(){
		this.outputManager.shutdown();
	}
}
