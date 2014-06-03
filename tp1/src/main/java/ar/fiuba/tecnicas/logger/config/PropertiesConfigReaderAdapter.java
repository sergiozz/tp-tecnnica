package ar.fiuba.tecnicas.logger.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.model.Level;

public class PropertiesConfigReaderAdapter extends ConfigReaderAdapter {

	private Properties properties;
	
	@Override
	public String getProperty(String key) {
		if (this.properties.containsKey(key)){
			return this.properties.getProperty(key);
		}else{
			return null;
		}
	}

	@Override
	public void loadConfig(String filename) throws  FileNotFoundException, MalformedConfigFileException {
		this.properties = new Properties();
		FileInputStream in;
		try{
			in = new FileInputStream(filename);
			this.properties.load(in);
			in.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
			return;
		}
	}

	@Override
	public List<OutputConfig> getOutputConfigs() throws MalformedConfigFileException{
		String[] pieces = this.getProperty(CONSOLE_CONFIG).split(DEFAULT_OUTPUT_CONFIG_SEPARATOR);
		Boolean logOnConsole = new Boolean(pieces[0]);
		List<OutputConfig> outputConfigs = new ArrayList<OutputConfig>();
		if (logOnConsole){
			OutputConfig consoleConfig = new OutputConfig(Level.valueOf(pieces[1]), null, OutputType.CONSOLE);
			outputConfigs.add(consoleConfig);
		}
		
		String[] files = this.getProperty(FILES_CONFIG).split(DEFAULT_FILE_SEPARATOR);
		
		for (String f : files){
			pieces = f.split(DEFAULT_OUTPUT_CONFIG_SEPARATOR);
			if (pieces.length != 2){
				throw new MalformedConfigFileException("expected : separator between filename and log filter in " + f + "\n");
			}
			OutputConfig fileConfig = new OutputConfig(Level.valueOf(pieces[1]), pieces[0], OutputType.FILE);
			outputConfigs.add(fileConfig);
		}
		
		return outputConfigs;
	}
}
