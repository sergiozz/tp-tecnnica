package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

public class MethodNameChainFormat extends ChainFormat {

	@Override
	public void format(Message message, StringBuffer buffer) {
		buffer.append(message.getMethodName());
	}

}
