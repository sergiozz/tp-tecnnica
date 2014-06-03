package ar.fiuba.tecnicas.logger.config;

import java.io.FileNotFoundException;
import java.util.List;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;

public abstract class ConfigReaderAdapter {
	
	public static final String DEFAULT_SEPARATOR = "-";
	public static final String DEFAULT_FILE_SEPARATOR = ",";
	public static final String DEFAULT_OUTPUT_CONFIG_SEPARATOR = ":";
	
	public static final String FILTER_CONFIG = "level";
	public static final String FILES_CONFIG = "file_output";
	public static final String CONSOLE_CONFIG = "console_output";
	public static final String FORMAT_CONFIG = "format";
	public static final String SEPARATOR_CONFIG = "separator";
	
	public abstract String getProperty(String key);
	public abstract void loadConfig(String filename) throws FileNotFoundException, MalformedConfigFileException;
	public abstract List<OutputConfig> getOutputConfigs() throws MalformedConfigFileException;
}
