package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

public abstract class AbstractMessageFormatter {

	public abstract String formatMessage(Message message);
}
