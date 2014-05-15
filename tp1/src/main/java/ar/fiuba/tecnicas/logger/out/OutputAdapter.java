package ar.fiuba.tecnicas.logger.out;

import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;

public abstract class OutputAdapter {
	protected MessageFormatter formatter;
	
	public OutputAdapter(MessageFormatter formatter){
		this.formatter = formatter;
	}
	
	public abstract void write(Message msg);
	public abstract void open(); 
	public abstract void close();
}
