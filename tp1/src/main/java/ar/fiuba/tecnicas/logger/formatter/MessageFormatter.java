package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.model.Message;

public abstract class MessageFormatter {
	protected Format format;
	
	public MessageFormatter(Format format){
		this.format = format;
	}
	
	public abstract String formatMessage(Message message);
}
