package ar.fiuba.tecnicas.logger.formatter;

import java.text.SimpleDateFormat;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea una fecha
 * 
 * 
 * */

public class DateChainFormat extends ChainFormat{

	private SimpleDateFormat simpleDateFormat;

	public DateChainFormat(SimpleDateFormat simpleDateFormat){
		this.simpleDateFormat = simpleDateFormat;
	}
	
	@Override
	public void format(Message message, StringBuffer buffer) {
		buffer.append(this.simpleDateFormat.format(message.getDate()));
	}

}
