package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea el mensaje de un usuario
 * 
 * 
 * */

public class UserMessageChainFormat extends ChainFormat {
    private static final String FIELDNAME = "Mensaje";

	@Override
	public String format(Message message) {
        return message.getUserMessage();
	}

    @Override
    public String getFieldName(){
        return this.FIELDNAME;
    }
}
