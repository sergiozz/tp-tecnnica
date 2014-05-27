package ar.fiuba.tecnicas.logger.exceptions;

import ar.fiuba.tecnicas.logger.config.OutputType;

public class UnknownOutputTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6557267260643482059L;
	
	private OutputType type;
	
	public UnknownOutputTypeException(OutputType type) {
		this.type = type;
	}

	public OutputType getType() {
		return type;
	}
	
	

}
