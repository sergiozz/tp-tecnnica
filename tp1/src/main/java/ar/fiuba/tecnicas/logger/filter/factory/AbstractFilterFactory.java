package ar.fiuba.tecnicas.logger.filter.factory;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.filter.AbstractFilter;

public abstract class AbstractFilterFactory {
	public abstract AbstractFilter createFilter(OutputConfig o);
}
