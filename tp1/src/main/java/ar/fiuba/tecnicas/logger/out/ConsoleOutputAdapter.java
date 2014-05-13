package ar.fiuba.tecnicas.logger.out;

import ar.fiuba.tecnicas.logger.model.Message;

public class ConsoleOutputAdapter extends OutputAdapter{

	@Override
	public void write(Message msg) {
		System.out.println(msg.getMessage());
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
