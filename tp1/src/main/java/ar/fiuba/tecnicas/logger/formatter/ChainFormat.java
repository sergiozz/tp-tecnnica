package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Clase generica que modela un formateador generico
 * 
 * 
 * */

public abstract class ChainFormat {
	public abstract String format(Message message);

    public abstract String getFieldName();
}
