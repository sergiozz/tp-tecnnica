package ar.fiuba.tecnicas.logger.formatter;

import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.model.Message;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONObject;

public class JSONMessageFormatter extends MessageFormatter{

    private List<ChainFormat> chainFormats;

	public JSONMessageFormatter(Format format) {
        super(format);

	}

	@Override
	public String formatMessage(Message message) {

        JSONObject obj = new JSONObject();
        for(ChainFormat f : this.chainFormats){
            String value = f.format(message);
            obj.put(f.getFieldName(), value);
        }

        return obj.toString();
	}

}
