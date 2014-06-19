package ar.fiuba.tecnicas.logger.outputFactory;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.out.ConsoleOutputAdapter;
import ar.fiuba.tecnicas.logger.out.OutputAdapter;

/*
 * Responsabilities: Clase descendiente de AbstractOutputFactory que conoce como crear objetos ConsoleOutput
 * 
 * 
 * */

public class ConsoleOutputFactory extends AbstractOutputFactory {

	@Override
	public OutputAdapter createOutput(OutputConfig o) {
		ConsoleOutputAdapter consoleAdapter = new ConsoleOutputAdapter();
		
		return consoleAdapter;
	}

}
