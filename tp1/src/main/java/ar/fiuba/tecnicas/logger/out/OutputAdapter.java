package ar.fiuba.tecnicas.logger.out;


import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.filter.AbstractFilter;
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
	protected AbstractFilter filter;
	
	public abstract void write(Message msg);
	public abstract void open(); 
	public abstract void close();

	public void setFormatter(MessageFormatter formatter) {
		this.formatter = formatter;
	}

	public void setOutputConfig(OutputConfig outputConfig) {
		this.outputConfig = outputConfig;
	}

	public void setFilter(AbstractFilter filter) {
		this.filter = filter;
	}
	
	
}
