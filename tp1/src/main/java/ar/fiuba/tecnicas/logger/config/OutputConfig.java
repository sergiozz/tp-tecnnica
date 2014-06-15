package ar.fiuba.tecnicas.logger.config;

import java.util.HashMap;
import java.util.Map;

import ar.fiuba.tecnicas.logger.filter.AbstractFilter;
import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.model.Level;

/*
 * Responsabilities: Modela la configuracion de un medio de salida
 *
 *
 * */

public class OutputConfig {
	
	public static final String FILTER_CLASS_NAME = "filter_type";
	public static final String OUTPUT_FACTORY_CLASS_NAME = "output_type";
	public static final String OUTPUT_NAME = "filename";
	public static final String FILTER_DATA = "filter_data";

	private Map<String,String> values;
	
	public OutputConfig(Map<String,String> values){
		this.values = values;
	}
	
	public String getValueForKey(String key){
		return this.values.get(key);
	}
	
}
