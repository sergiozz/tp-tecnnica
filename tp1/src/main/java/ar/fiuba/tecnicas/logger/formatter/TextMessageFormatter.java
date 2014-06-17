package ar.fiuba.tecnicas.logger.formatter;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.model.Message;

/*
 * Responsabilities: Formatea un mensaje basado en el formato especificado
 * en el archivo de properties. Crea una lista de ChainFormats 
 * que procesan un objeto Message a la hora de tansformarlo en un String
 * para loguearlo en un archivo o consola.
 * 
 * 
 * */

public class TextMessageFormatter extends MessageFormatter{

	
	public TextMessageFormatter(Format format) {
		super(format);
	}

	@Override
	public String formatMessage(Message message){
		StringBuffer formattedMessage = new StringBuffer();
		
		for(ChainFormat f : this.chainFormats){
            formattedMessage.append(f.format(message));
			formattedMessage.append(this.format.getSeparator());
		}
		
		return formattedMessage.toString();
	}
}
