package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea un nivel
 * 
 * 
 * */

public class LevelChainFormat extends ChainFormat {
    private static final String FIELDNAME = "Nivel";

	@Override
	public String format(Message message) {
        return message.getLevel().getName();
	}

    @Override
    public String getFieldName(){
        return this.FIELDNAME;
    }
}
