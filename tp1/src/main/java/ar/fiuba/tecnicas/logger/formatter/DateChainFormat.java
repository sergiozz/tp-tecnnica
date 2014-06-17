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
    private static final String FIELDNAME = "Fecha";

	public DateChainFormat(SimpleDateFormat simpleDateFormat){
		this.simpleDateFormat = simpleDateFormat;
	}
	
	@Override
	public String format(Message message) {
		return this.simpleDateFormat.format(message.getDate());
	}

    @Override
    public String getFieldName(){
        return this.FIELDNAME;
    }

}
