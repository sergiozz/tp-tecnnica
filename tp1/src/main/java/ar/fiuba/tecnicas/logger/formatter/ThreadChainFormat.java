package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea un nombre de thread
 * 
 * 
 * */

public class ThreadChainFormat extends ChainFormat{

	@Override
	public void format(Message message, StringBuffer buffer) {
		buffer.append(message.getThreadId());
	}

}
