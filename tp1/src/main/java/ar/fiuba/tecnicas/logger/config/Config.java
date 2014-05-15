package ar.fiuba.tecnicas.logger.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Config {
	private Properties properties;

    private static final HashMap<String,Integer> HashLevels = new HashMap<String,Integer>();
    private static final String DEBUG = "DEBUG";
    private static final String INFO = "INFO";
    private static final String WARN = "WARN";
    private static final String ERROR = "ERROR";
    private static final String FATAL = "FATAL";
    private static final String OFF = "OFF";

	private static final String FILTER_CONFIG = "filter";
	private static final String FILES_CONFIG = "file_output";
	private static final String CONSOLE_CONFIG = "console_output";
	private static final String FORMAT_CONFIG = "format";
	private static final String SEPARATOR_CONFIG = "separator";

    public Config(String configFilename){
        chargeHashLevels();
		this.properties = new Properties();
		FileInputStream in;
		try{
			in = new FileInputStream(configFilename);
			this.properties.load(in);
			in.close();
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
			return;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return;
		}
	}
	
	private String getProperty(String key){
		if (this.properties.containsKey(key)){
			return this.properties.getProperty(key);
		}else{
			return null;
		}
	}
	
	public String getFilter(){
		return this.getProperty(FILTER_CONFIG);
	}
	
	public String[] getFiles(){
		String allFiles = this.getProperty(FILES_CONFIG);
		return allFiles.split(",");
	}
	
	public Boolean logOnConsole(){
		return new Boolean(this.getProperty(CONSOLE_CONFIG));
	}
	
	public String getFormat(){
		return this.getProperty(FORMAT_CONFIG);
	}
	
	public String getSeparator(){
		return this.getProperty(SEPARATOR_CONFIG);
	}

    public Integer getLevelFilter(String key){
        return this.HashLevels.get(key);
    }

    public Integer getLevelFilterOfConfig() {
        return this.getLevelFilter(this.getFilter());
    }

    private void chargeHashLevels(){
        HashLevels.put("DEBUG",50);
        HashLevels.put("INFO",40);
        HashLevels.put("WARN",30);
        HashLevels.put("ERROR",20);
        HashLevels.put("FATAL",10);
        HashLevels.put("OFF",0);
    }
}
