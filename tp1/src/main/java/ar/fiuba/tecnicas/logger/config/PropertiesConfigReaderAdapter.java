package ar.fiuba.tecnicas.logger.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.model.Format;

/*
 * Responsabilities: Provee acceso a un archivo de tipo properties e interpreta su contenido.
 * 
 * 
 * */

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
	public void loadConfig(String filename) throws  FileNotFoundException{
		this.properties = new Properties();
		FileInputStream in;
		try{
			in = new FileInputStream(filename);
			this.properties.load(in);
			in.close();
		}catch (FileNotFoundException e){
			throw e;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return;
		}
	}

	@Override
	public List<OutputConfig> getOutputConfigs() throws MalformedConfigFileException{
		
		List<OutputConfig> outputConfigs = new ArrayList<OutputConfig>();
		JSONParser parser = new JSONParser();
		try{
			JSONObject jObject = (JSONObject)parser.parse(this.getProperty(ConfigReaderAdapter.OUTPUTS_CONFIG));
			JSONArray jsonArray = (JSONArray)jObject.get(ConfigReaderAdapter.OUTPUTS_CONFIG);
			Iterator<JSONObject> jsonOutputs = (Iterator<JSONObject>)jsonArray.iterator(); 
			
			while(jsonOutputs.hasNext()){
				JSONObject j = jsonOutputs.next();
				HashMap<String, String> map = new HashMap<String, String>();
				Set<String> keys = j.keySet();
				for (String k : keys){
					String value = (String)j.get(k);
					map.put(k, value);
				}
				this.replaceAlias(map, OutputConfig.OUTPUT_FACTORY_CLASS_NAME);
				this.replaceAlias(map, OutputConfig.FILTER_FACTORY_CLASS_NAME);
				OutputConfig outputConfig = new OutputConfig(map);
				outputConfigs.add(outputConfig);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return outputConfigs;
	}

	private void replaceAlias(HashMap<String, String> map, String tag) throws MalformedConfigFileException{
		String factoryClassName = this.getProperty(map.get(tag));
		if (factoryClassName == null){
			String message = "No existe la propiedad " + map.get(OutputConfig.OUTPUT_FACTORY_CLASS_NAME);
			throw new MalformedConfigFileException(message);
		}
		map.put(tag, factoryClassName);
	}

	@Override
	public Format getFormat() {
		String separator = this.getProperty(ConfigReaderAdapter.SEPARATOR_CONFIG);
		if (separator == null){separator = ConfigReaderAdapter.DEFAULT_SEPARATOR;}
		String format = this.getProperty(ConfigReaderAdapter.FORMAT_CONFIG);
		String formatType = this.getProperty(ConfigReaderAdapter.FORMAT_TYPE);
		String formatterClassName = this.getProperty(formatType);
		return new Format(format, formatterClassName, separator);
	}

}
