package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea un nombre de archivo
 * 
 * 
 * */

public class FilenameChainFormat extends ChainFormat {

    private static final String FIELDNAME = "Archivo";

	@Override
	public String format(Message message) {
        return message.getFilename();
	}

    @Override
    public String getFieldName(){
        return this.FIELDNAME;
    }
}
