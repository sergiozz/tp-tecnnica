package ar.fiuba.tecnicas.logger.model;

/*
 * Responsabilities: Modelar un nivel de logueo
 * 
 * 
 * */

public enum Level{
    TRACE(6, "TRACE"), DEBUG(5, "DEBUG"), INFO(4, "INFO"), WARN(3, "WARN"), ERROR(2, "ERROR"), FATAL(1, "FATAL"), OFF(0, "OFF");
	
	private int value;
	private String name;
	
	Level(int value, String name){
		this.value = value;
		this.name = name;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getName(){
		return this.name;
	}
	
}