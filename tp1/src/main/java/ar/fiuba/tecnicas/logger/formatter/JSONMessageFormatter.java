package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.model.Message;

public class JSONMessageFormatter extends MessageFormatter{

	public JSONMessageFormatter(Format format) {
		super(format);
	}

	@Override
	public String formatMessage(Message message) {
		return null;
	}

}
