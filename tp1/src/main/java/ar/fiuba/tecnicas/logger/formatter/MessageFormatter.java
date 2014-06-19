package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.model.Message;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.LinkedList;

/*
 * Responsabilities: Clase base abstracta que provee una interfaz comun a los formatters ademas de proveer una forma de parsear el formato crudo
 * que toma desde la clase Format. 
 * 
 * 
 * */

public abstract class MessageFormatter {
	protected Format format;
    protected List<ChainFormat> chainFormats;
	
	public MessageFormatter(Format format){
		this.format = format;
        this.chainFormats = new LinkedList<ChainFormat>();
        this.parseFormat();

    }

    protected void parseFormat(){

        String[] tokens = this.format.getFormatString().split("%");

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
                case 'g':
                    this.chainFormats.add(new LoggerNameChainFormat());
                    break;
            }
        }
    }
	
	public abstract String formatMessage(Message message);
}
