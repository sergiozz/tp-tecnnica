package ar.fiuba.tecnicas.logger.outputFactory;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.out.OutputAdapter;

/*
 * Responsabilities: Clase base que provee la interfaz comun para las fabricas de outputs
 * 
 * 
 * */

public abstract class AbstractOutputFactory {

	public abstract OutputAdapter createOutput(OutputConfig o);
}
