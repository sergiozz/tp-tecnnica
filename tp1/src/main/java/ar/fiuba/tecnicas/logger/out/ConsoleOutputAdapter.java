package ar.fiuba.tecnicas.logger.out;

import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;


public class ConsoleOutputAdapter extends OutputAdapter{

	public ConsoleOutputAdapter(MessageFormatter formatter){
		super(formatter);
	}
	
	@Override
	public void write(Message msg) {
		System.out.println(this.formatter.formatMessage(msg));
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
