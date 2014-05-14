package ar.fiuba.tecnicas.logger.out;

import java.util.LinkedList;
import java.util.List;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Message;

public class OutputManager {

	private List<OutputAdapter> outputs;
		
	public OutputManager(Config config){
		this.outputs = new LinkedList<OutputAdapter>();
		if (config.logOnConsole()){
			this.addOutput(new ConsoleOutputAdapter());
		}
		
		for (String f : config.getFiles()){
			this.addOutput(new FileOutputAdapter(f));
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
