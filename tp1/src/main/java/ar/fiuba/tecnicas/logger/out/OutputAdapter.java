package ar.fiuba.tecnicas.logger.out;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.formatter.TextMessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Clase generica que engloba a los adapters
 * 
 * 
 * */

public abstract class OutputAdapter {
	protected TextMessageFormatter formatter;
	protected OutputConfig outputConfig;
	
	public OutputAdapter(TextMessageFormatter formatter, OutputConfig outputConfig){
		this.formatter = formatter;
		this.outputConfig = outputConfig;
	}
	
	public abstract void write(Message msg);
	public abstract void open(); 
	public abstract void close();
}
