package ar.fiuba.tecnicas.logger.filter;

import ar.fiuba.tecnicas.logger.model.Level;
import ar.fiuba.tecnicas.logger.model.Message;


/*
 * Responsabilities: Clase que implementa AbstractFilter y provee filtro por nivel.
 * 
 * 
 * */

public class LevelFilter extends AbstractFilter {

	private Level level;
	
	public LevelFilter(){
	}
	
	public void setLevel(Level level){
		
		this.level = level;
	}
	
	@Override
	public Boolean filter(Message message) {
		return message.getLevel().getValue() <= this.level.getValue();
	}

}
