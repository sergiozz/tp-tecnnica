package ar.fiuba.tecnicas.logger.outputFactory;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.out.OutputAdapter;

public abstract class AbstractOutputFactory {

	public abstract OutputAdapter createOutput(OutputConfig o);
}
