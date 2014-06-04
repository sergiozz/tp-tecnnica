package ar.fiuba.tecnicas.logger.app;

import java.io.FileNotFoundException;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.model.Level;
import ar.fiuba.tecnicas.logger.model.Message;
import ar.fiuba.tecnicas.logger.out.OutputManager;


/*
 * Responsabilities: Clase que presenta los servicios de logueo al usuario
 * 
 * 
 * */

public class Logger {
    private static final String DEFAULT_PROPERTIES = "../resurces/default.properties";
	private static final String CONFIG_FILE_PROPERTIES = "../resurces/logger-config.properties";
	private static final String CONFIG_FILE_XML = "../resurces/logger-config.xml";
    private Config config;
	private OutputManager outputManager;
    private LogProcessor logProcessor;

    public Logger() throws MalformedConfigFileException{
    	this.config = getConfigFile(CONFIG_FILE_PROPERTIES);
    	if (this.config == null){
    		this.config = getConfigFile(CONFIG_FILE_XML);
    		if (this.config == null){
    			this.config = getConfigFile(DEFAULT_PROPERTIES);
    		}
    	}
    	this.outputManager = new OutputManager(this.config);
    	this.logProcessor = new LogProcessor(this.config);
    }
    
    
    
    private Config getConfigFile(String filename) throws MalformedConfigFileException{
    	try{
    		Config c = new Config(filename);
    		return c;
    	}catch(FileNotFoundException e){
    		return null;    		
    	}catch(MalformedConfigFileException e ){
    		throw e;
    	}
	}



	public void trace(String userMessage){log(userMessage, Level.TRACE);}

    public void debug(String userMessage){log(userMessage, Level.DEBUG);}

    public void info(String userMessage){log(userMessage, Level.INFO);}

    public void warn(String userMessage){log(userMessage, Level.WARN);}

    public void error(String userMessage){log(userMessage, Level.ERROR);}

    public void fatal(String userMessage){log(userMessage, Level.FATAL);}

    private void log(String userMessage, Level level){
		Message message = logProcessor.processMessage(userMessage, level);
		if (message != null){
			this.outputManager.write(message);        
		}
	}
	
	public Boolean isTrace(){
		return (Level.TRACE.getValue() <= this.config.getLevel().getValue());
	}

	public Boolean isDebug(){
		return (Level.DEBUG.getValue() <= this.config.getLevel().getValue());
	}

	public Boolean isInfo(){
		return (Level.INFO.getValue() <= this.config.getLevel().getValue());
	}
	
	public Boolean isWarning(){
		return (Level.WARN.getValue() <= this.config.getLevel().getValue());
	}
	
	public Boolean isError(){
		return (Level.ERROR.getValue() <= this.config.getLevel().getValue());
	}
	
	public Boolean isFatal(){
		return (Level.FATAL.getValue() <= this.config.getLevel().getValue());
	}
	
	public void close(){
		this.outputManager.shutdown();
	}
}
