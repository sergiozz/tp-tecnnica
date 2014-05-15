package ar.fiuba.tecnicas.logger.formatter;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import ar.fiuba.tecnicas.logger.model.Message;

public class MessageFormatter {
	private String format;
	private List<ChainFormat> chainFormats;
	
	public MessageFormatter(String format) {
		this.format = format;
		this.chainFormats = new LinkedList<ChainFormat>();
		
		String[] tokens = format.split("%");
		
		//TODO: mejorar esto a un mapa estatico
		
		for (String t : tokens){
			if (t.isEmpty()){
				continue;
			}
			switch (t.charAt(0)){
			case 'd':
				String dateFormat = t.substring(2, t.lastIndexOf("}"));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
				this.chainFormats.add(new DateChainFormat(simpleDateFormat));
				break;
			case 't':
				this.chainFormats.add(new ThreadChainFormat());
				break;
			case 'm':
				this.chainFormats.add(new UserMessageChainFormat());
				break;
			case 'p':
				this.chainFormats.add(new LevelChainFormat());
				break;
			case 'L':
				this.chainFormats.add(new LineChainFormat());
				break;
			case 'F':
				this.chainFormats.add(new FilenameChainFormat());
				break;
			case 'M':
				this.chainFormats.add(new MethodNameChainFormat());
				break;
			}
		}
	}

	public String formatMessage(Message message){
		StringBuffer formattedMessage = new StringBuffer();
		
		for(ChainFormat f : this.chainFormats){
			f.format(message, formattedMessage);
		}
		
		return formattedMessage.toString();
	}
}
