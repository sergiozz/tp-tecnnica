package ar.fiuba.tecnicas.logger.out;

import java.util.LinkedList;
import java.util.List;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.config.OutputType;
import ar.fiuba.tecnicas.logger.exceptions.UnknownOutputTypeException;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Administra los OutputAdapters
 * 
 * 
 * */

public class OutputManager {

	private List<OutputAdapter> outputs;
	private MessageFormatter formatter;	
	
	public OutputManager(Config config){
		this.outputs = new LinkedList<OutputAdapter>();
		MessageFormatter formatter = new MessageFormatter(config.getFormat(), config.getSeparator());
		for (OutputConfig o : config.getOutputConfigs()){
			try{
				this.addOutput(OutputFactory.createOutput(o, formatter));
			}catch(UnknownOutputTypeException e){
				System.err.println(e.getMessage() + e.getType().toString());
			}
		}
	}

	public void addOutput(OutputAdapter o){
		this.outputs.add(o);
	}
		
	public void write(Message msg){
		for(OutputAdapter o : this.outputs){
			o.write(msg);
		}
	}

	public void shutdown(){
		for(OutputAdapter o : this.outputs){
			o.close();
		}
	}
}
