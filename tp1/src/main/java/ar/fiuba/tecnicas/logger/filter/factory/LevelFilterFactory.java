package ar.fiuba.tecnicas.logger.filter.factory;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.filter.AbstractFilter;
import ar.fiuba.tecnicas.logger.filter.LevelFilter;
import ar.fiuba.tecnicas.logger.model.Level;

public class LevelFilterFactory extends AbstractFilterFactory {

	@Override
	public AbstractFilter createFilter(OutputConfig o) {
		LevelFilter filter = new LevelFilter();
		filter.setLevel(Level.valueOf(o.getValueForKey(OutputConfig.FILTER_DATA)));
		return filter;
	}

}
