package ar.fiuba.tecnicas.logger.outputFactory;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.out.FileOutputAdapter;
import ar.fiuba.tecnicas.logger.out.OutputAdapter;

/*
 * Responsabilities: Clase descendiente de AbstractOutputFactory que conoce como crear objetos FileOutputFactory
 * 
 * 
 * */

public class FileOutputFactory extends AbstractOutputFactory{

	private static final String FILENAME_KEY = "filename";

	@Override
	public OutputAdapter createOutput(OutputConfig o) {
		FileOutputAdapter fileAdapter = new FileOutputAdapter(o.getValueForKey(FILENAME_KEY));
		
		return fileAdapter;
	}

}
