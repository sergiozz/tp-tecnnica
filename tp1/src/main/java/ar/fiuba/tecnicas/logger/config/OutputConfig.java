package ar.fiuba.tecnicas.logger.config;

import ar.fiuba.tecnicas.logger.model.Level;

public class OutputConfig {
	private Level filter;
	private String path;
	private OutputType type;
	
	public OutputConfig(Level filter, String path, OutputType type){
		this.filter = filter;
		this.path = path;
		this.type = type;
	}
	
	public Level getFilter() {
		return filter;
	}
	public String getPath() {
		return path;
	}

	public OutputType getType() {
		return type;
	}
	
	
}
