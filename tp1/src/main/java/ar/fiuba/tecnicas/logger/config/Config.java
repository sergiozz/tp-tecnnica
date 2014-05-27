package ar.fiuba.tecnicas.logger.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import ar.fiuba.tecnicas.logger.model.Level;

/*
 * Responsabilities: Clase Proxy para trabajar con el archivo de configuracion
 * 
 * 
 * */

public class Config {
	private Properties properties;

	private static final String FILTER_CONFIG = "level";
	private static final String FILES_CONFIG = "file_output";
	private static final String CONSOLE_CONFIG = "console_output";
	private static final String FORMAT_CONFIG = "format";
	private static final String SEPARATOR_CONFIG = "separator";

	private static final String DEFAULT_SEPARATOR = "-";
	private static final String DEFAULT_FILE_SEPARATOR = ",";
	private static final String DEFAULT_OUTPUT_CONFIG_SEPARATOR = ":";
	
	private List<OutputConfig> outputConfigs;

    public Config(String configFilename){
		this.properties = new Properties();
		this.outputConfigs = new LinkedList<OutputConfig>();
		FileInputStream in;
		try{
			in = new FileInputStream(configFilename);
			this.properties.load(in);
			this.loadOutputConfigs();
			in.close();
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
			return;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return;
		}
	}
	
	private void loadOutputConfigs() {
		String[] pieces = this.getProperty(CONSOLE_CONFIG).split(DEFAULT_OUTPUT_CONFIG_SEPARATOR);
		Boolean logOnConsole = new Boolean(pieces[0]);
		if (logOnConsole){
			OutputConfig consoleConfig = new OutputConfig(Level.valueOf(pieces[1]), null, OutputType.CONSOLE);
			this.outputConfigs.add(consoleConfig);
		}
		
		String[] files = this.getProperty(FILES_CONFIG).split(DEFAULT_FILE_SEPARATOR);
		
		for (String f : files){
			pieces = f.split(DEFAULT_OUTPUT_CONFIG_SEPARATOR);
			OutputConfig fileConfig = new OutputConfig(Level.valueOf(pieces[1]), pieces[0], OutputType.FILE);
			this.outputConfigs.add(fileConfig);
		}
	}

	private String getProperty(String key){
		if (this.properties.containsKey(key)){
			return this.properties.getProperty(key);
		}else{
			return null;
		}
	}
	
	public Level getLevel(){
		return Level.valueOf(this.getProperty(FILTER_CONFIG));
	}
	
	public List<OutputConfig> getOutputConfigs(){
		return this.outputConfigs;
	}
	
	public String getFormat(){
		return this.getProperty(FORMAT_CONFIG);
	}
	
	public String getSeparator(){
		String separator = this.getProperty(SEPARATOR_CONFIG);
		if (separator != null && !separator.isEmpty()){
			
			return separator;
		}
		return DEFAULT_SEPARATOR;
	}

}
