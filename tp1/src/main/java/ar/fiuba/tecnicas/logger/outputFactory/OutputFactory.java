package ar.fiuba.tecnicas.logger.outputFactory;


import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.ConfigReaderAdapter;
import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.exceptions.UnknownOutputTypeException;
import ar.fiuba.tecnicas.logger.filter.AbstractFilter;
import ar.fiuba.tecnicas.logger.formatter.JSONMessageFormatter;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.formatter.TextMessageFormatter;
import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.out.ConsoleOutputAdapter;
import ar.fiuba.tecnicas.logger.out.FileOutputAdapter;
import ar.fiuba.tecnicas.logger.out.OutputAdapter;

/*
 * Responsabilities: Factory para el tipo de salida requerido
 *
 *
 * */

public class OutputFactory {

	public static OutputAdapter createOutput(Config config, OutputConfig outputConfig, Format format)
		throws UnknownOutputTypeException{
		
		try{
			String factoryName = outputConfig.getValueForKey(OutputConfig.OUTPUT_FACTORY_CLASS_NAME); 
			AbstractOutputFactory factory = (AbstractOutputFactory)Class.forName(factoryName).newInstance();
			OutputAdapter output = factory.createOutput(outputConfig);
			
			MessageFormatter formatter = format.getMessageFormatter();
			AbstractFilter filter = OutputFactory.createFilter(outputConfig);
			 
			output.setFormatter(formatter);
			output.setOutputConfig(outputConfig);
			output.setFilter(filter);
			return output;
		}catch(Exception e){
			return null;
		}
			
			
	}

	private static AbstractFilter createFilter(OutputConfig o) {
		String filterName = o.getValueForKey(OutputConfig.FILTER_CLASS_NAME);
		try{
			AbstractFilter filter = (AbstractFilter)Class.forName(filterName).newInstance();
			filter.setData(o.getValueForKey(OutputConfig.FILTER_DATA));
			return filter;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
