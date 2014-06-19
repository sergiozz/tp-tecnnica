package ar.fiuba.tecnicas.logger.filter;

import ar.fiuba.tecnicas.logger.model.Message;


/*
 * Responsabilities: Clase base abstracta que modela un filtro.
 * 
 * 
 * */

public abstract class AbstractFilter {

	public abstract Boolean filter(Message message);
}
