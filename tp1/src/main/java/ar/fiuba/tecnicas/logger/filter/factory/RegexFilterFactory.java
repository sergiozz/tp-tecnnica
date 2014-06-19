package ar.fiuba.tecnicas.logger.filter.factory;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.filter.AbstractFilter;
import ar.fiuba.tecnicas.logger.filter.LevelFilter;
import ar.fiuba.tecnicas.logger.filter.RegexFilter;


/*
 * Responsabilities: Clase que crea un RegexFilter y le coloca la informacion necesaria para que trabaje.
 * 
 * 
 * */

public class RegexFilterFactory extends AbstractFilterFactory {

	@Override
	public AbstractFilter createFilter(OutputConfig o) {
		RegexFilter filter = new RegexFilter();
		filter.setRegex(o.getValueForKey(OutputConfig.FILTER_DATA));
		return filter;
	}

}
