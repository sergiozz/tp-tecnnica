package ar.fiuba.tecnicas.logger.out;

import java.util.LinkedList;
import java.util.List;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.exceptions.UnknownOutputTypeException;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.formatter.TextMessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;
import ar.fiuba.tecnicas.logger.outputFactory.OutputFactory;

/*
 * Responsabilities: Administra los OutputAdapters
 * 
 * 
 * */

public class OutputManager {

	private List<OutputAdapter> outputs;
	
	public OutputManager(Config config){
		this.outputs = new LinkedList<OutputAdapter>();
		for (OutputConfig o : config.getOutputConfigs()){
			try{
				this.addOutput(OutputFactory.createOutput(config, o, config.getFormat()));
			}catch(UnknownOutputTypeException e){
				System.err.println(e.getMessage() + e.getType().toString());
			}
		}
	}

	private void addOutput(OutputAdapter o){
		o.open();
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
