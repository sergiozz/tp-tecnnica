package ar.fiuba.tecnicas.logger.model;

import java.lang.reflect.Constructor;

import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;

/*
 * Responsabilities: Modela un formato de mensaje.
 * 
 * */

public class Format {
	private String format;
	private MessageFormatter formatter;
	private String formatClassName;
	private String separator;
	
	public Format (String format, String formatClassName, String separator){
		this.format = format;
		this.formatClassName = formatClassName;
		this.formatter = null;
		this.separator = separator;
	}

	public String getFormatString() {
		return format;
	}

	public MessageFormatter getMessageFormatter() {
		if (this.formatter == null){
			try{
				Class<MessageFormatter> formatterClass = (Class<MessageFormatter>)Class.forName(this.formatClassName);
			
				Class[] classes = new Class[1];
				classes[0] = Format.class; 
				Constructor<? extends MessageFormatter> constructor = formatterClass.getConstructor(classes);
				this.formatter = constructor.newInstance(this);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return formatter;			
	}

	public String getSeparator() {
		return separator;
	}
	
	
}
