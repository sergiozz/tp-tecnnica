package ar.fiuba.tecnicas.logger.out;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Clase generica que engloba a los adapters
 * 
 * 
 * */

public abstract class OutputAdapter {
	protected MessageFormatter formatter;
	protected OutputConfig outputConfig;
	
	public OutputAdapter(MessageFormatter formatter, OutputConfig outputConfig){
		this.formatter = formatter;
		this.outputConfig = outputConfig;
	}
	
	public abstract void write(Message msg);
	public abstract void open(); 
	public abstract void close();
}
