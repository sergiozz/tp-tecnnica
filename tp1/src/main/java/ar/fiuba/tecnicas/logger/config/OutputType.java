package ar.fiuba.tecnicas.logger.config;

/*
 * Responsabilities: Modelar un tipo de salida
 *
 *
 * */

 public enum OutputType {
	FILE("file"), CONSOLE("console");
	
	private String type;
	
	OutputType(String type){
		this.type = type;
	}
}
