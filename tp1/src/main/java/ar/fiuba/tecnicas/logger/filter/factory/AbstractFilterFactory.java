package ar.fiuba.tecnicas.logger.filter.factory;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.filter.AbstractFilter;


/*
 * Responsabilities: Clase base abstracta que define la interfaz de una fabrica de filtros. 
 * 
 * 
 * */

public abstract class AbstractFilterFactory {
	public abstract AbstractFilter createFilter(OutputConfig o);
}
