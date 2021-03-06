package ar.fiuba.tecnicas.logger.out;


import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.formatter.TextMessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Escribe un objeto Message a consola
 * 
 * 
 * */

public class ConsoleOutputAdapter extends OutputAdapter{

	@Override
	public void write(Message msg) {
		if (this.filter.filter(msg)){
			System.out.println(this.formatter.formatMessage(msg));
		}
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
