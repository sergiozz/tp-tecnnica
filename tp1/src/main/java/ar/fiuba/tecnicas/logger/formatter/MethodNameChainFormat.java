package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea un nombre de metodo
 * 
 * 
 * */

public class MethodNameChainFormat extends ChainFormat {
    private static final String FIELDNAME = "Metodo";

	@Override
	public String format(Message message) {
        return message.getMethodName();
	}

    @Override
    public String getFieldName(){
        return this.FIELDNAME;
    }
}
