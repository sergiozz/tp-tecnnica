package ar.fiuba.tecnicas.logger.out;

import java.util.LinkedList;
import java.util.List;

import ar.fiuba.tecnicas.logger.config.Config;
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
		if (config.logOnConsole()){
			this.addOutput(new ConsoleOutputAdapter(formatter));
		}
		
		for (String f : config.getFiles()){
			FileOutputAdapter fileOutput = new FileOutputAdapter(f, formatter);
			fileOutput.open();
			this.addOutput(fileOutput);
			
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
