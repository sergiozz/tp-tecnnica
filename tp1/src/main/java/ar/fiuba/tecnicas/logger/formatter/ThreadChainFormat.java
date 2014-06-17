package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea un nombre de thread
 * 
 * 
 * */

public class ThreadChainFormat extends ChainFormat{
    private static final String FIELDNAME = "Thread";

	@Override
	public String format(Message message) {
        return (new Long(message.getThreadId())).toString();
	}

    @Override
    public String getFieldName(){
        return this.FIELDNAME;
    }
}
