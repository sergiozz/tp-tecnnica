package ar.fiuba.tecnicas.logger.config;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.model.Format;

public abstract class ConfigReaderAdapter {
	
	public static final String DEFAULT_SEPARATOR = "-";
	public static final String DEFAULT_FILE_SEPARATOR = ",";
	public static final String DEFAULT_OUTPUT_CONFIG_SEPARATOR = ":";
	
	public static final String FILTER_CONFIG = "level";
	public static final String OUTPUTS_CONFIG = "outputs";
	public static final String OUTPUT_CONFIG = "output";
	public static final String OUTPUT_TYPE = "output_type";
	public static final String OUTPUT_FILENAME = "filename";
	public static final String OUTPUT_FILTER_TYPE = "filter_type";
	
	
	
	public static final String FORMAT_CONFIG = "format";
	public static final String FORMAT_TYPE = "format_type";
	public static final String SEPARATOR_CONFIG = "separator";
	public static final String OUTPUT_FORMATTERS = "format_types";
	
	public abstract String getProperty(String key);
	public abstract void loadConfig(String filename) throws FileNotFoundException, MalformedConfigFileException;
	public abstract List<OutputConfig> getOutputConfigs() throws MalformedConfigFileException;
	public abstract Format getFormat();
	
}
