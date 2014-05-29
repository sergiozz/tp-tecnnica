package ar.fiuba.tecnicas.logger.out;

import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.config.OutputType;
import ar.fiuba.tecnicas.logger.exceptions.UnknownOutputTypeException;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;

/*
 * Responsabilities: Factory para el tipo de salida requerido
 *
 *
 * */

public class OutputFactory {

	public static OutputAdapter createOutput(OutputConfig o, MessageFormatter formatter)
		throws UnknownOutputTypeException{
		if (o.getType() == OutputType.CONSOLE){
			return new ConsoleOutputAdapter(formatter, o);			
		}else if (o.getType() == OutputType.FILE){
			FileOutputAdapter fileOutput = new FileOutputAdapter(formatter, o);
			fileOutput.open();
			return fileOutput;			
		}else{
			throw new UnknownOutputTypeException(o.getType());
		}
	}
}
