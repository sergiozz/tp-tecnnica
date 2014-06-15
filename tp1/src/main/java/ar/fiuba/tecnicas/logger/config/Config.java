package ar.fiuba.tecnicas.logger.config;

import java.io.FileNotFoundException;
import java.util.List;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.model.Level;

/*
 * Responsabilities: Clase Proxy para trabajar con el archivo de configuracion
 * 
 * 
 * */

public class Config {
	
	private static final String PROPERTIES_SUFFIX = ".properties";

	private ConfigReaderAdapter configReader;
	
	private List<OutputConfig> outputConfigs;
	
    public Config(String configFilename) throws FileNotFoundException, MalformedConfigFileException{
		try{
	    	if (configFilename.endsWith(PROPERTIES_SUFFIX)){
				this.configReader = new PropertiesConfigReaderAdapter();
			}else{
				this.configReader = new XMLConfigReaderAdapter();
			}
	    	this.configReader.loadConfig(configFilename);
	    	this.outputConfigs = this.configReader.getOutputConfigs();
	    	
		}catch(FileNotFoundException e){
			System.err.println(e.getMessage());
			throw e;
		}
	}

	public Level getLevel(){
		return Level.valueOf(this.configReader.getProperty(ConfigReaderAdapter.FILTER_CONFIG));
	}
	
	public List<OutputConfig> getOutputConfigs(){
		return this.outputConfigs;
	}

	public Format getFormat(){
		return this.configReader.getFormat();
	}

}