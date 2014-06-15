package ar.fiuba.tecnicas.logger.filter;

import ar.fiuba.tecnicas.logger.model.Message;

public abstract class AbstractFilter {

	public abstract Boolean filter(Message message);
	public abstract void setData(String data);
}
