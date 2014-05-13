package ar.fiuba.tecnicas.logger.out;

import ar.fiuba.tecnicas.logger.model.Message;

public abstract class OutputAdapter {
	public abstract void write(Message msg);
	public abstract void open(); 
	public abstract void close();
}
