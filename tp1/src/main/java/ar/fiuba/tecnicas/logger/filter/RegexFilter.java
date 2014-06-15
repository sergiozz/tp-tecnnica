package ar.fiuba.tecnicas.logger.filter;

import ar.fiuba.tecnicas.logger.model.Message;

public class RegexFilter extends AbstractFilter {

	private String regex;
	
	public RegexFilter(){
	}

	public void setData(String data){
		this.regex = data;
		
	}
	@Override
	public Boolean filter(Message message) {
		return message.getMessage().matches(this.regex);
	}

}
