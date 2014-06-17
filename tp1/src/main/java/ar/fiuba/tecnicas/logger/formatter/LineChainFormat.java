package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea una linea
 * 
 * 
 * */

public class LineChainFormat extends ChainFormat {
    private static final String FIELDNAME = "Linea";

	@Override
	public String format(Message message) {
        return (new Integer (message.getLine())).toString();
	}

    @Override
    public String getFieldName(){
        return this.FIELDNAME;
    }
}
