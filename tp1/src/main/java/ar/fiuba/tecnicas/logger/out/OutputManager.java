package ar.fiuba.tecnicas.logger.out;

import java.util.LinkedList;
import java.util.List;

import ar.fiuba.tecnicas.logger.model.Message;

public class OutputManager {

	private List<OutputAdapter> outputs;
		
	public OutputManager(){
		this.outputs = new LinkedList<OutputAdapter>();
		
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
