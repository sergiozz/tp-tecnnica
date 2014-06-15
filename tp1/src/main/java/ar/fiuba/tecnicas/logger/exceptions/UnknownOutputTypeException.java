package ar.fiuba.tecnicas.logger.exceptions;


public class UnknownOutputTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6557267260643482059L;
	
	private String type;
	
	public UnknownOutputTypeException(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	

}
