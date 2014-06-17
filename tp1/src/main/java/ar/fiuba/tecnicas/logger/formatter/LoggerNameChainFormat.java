package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

public class LoggerNameChainFormat extends ChainFormat {
    private static final String FIELDNAME = "Logger";

	@Override
	public String format(Message message) {
        return message.getLoggerModule();
	}

    @Override
    public String getFieldName(){
        return this.FIELDNAME;
    }
}
