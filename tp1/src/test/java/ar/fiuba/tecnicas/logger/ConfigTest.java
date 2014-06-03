package ar.fiuba.tecnicas.logger;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.model.Level;

public class ConfigTest {

	private static final String CONFIG_PATH = "src/test/resources/";
	
	@Test
	public void loadConfigTest(){
		try{
		Config config = 
				new Config(CONFIG_PATH + "default.properties");
		
		assertEquals(config.getLevel(), Level.DEBUG);
		assertEquals(config.getFormat(), "%d{HH:mm:ss}-%p-%t-%m");
		assertEquals(config.getSeparator(), "-");
		assertEquals(config.getOutputConfigs().get(0).getPath(), null);
		assertEquals(config.getOutputConfigs().get(1).getPath(), "file1");
		assertEquals(config.getOutputConfigs().get(2).getPath(), "file2");
		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void loadMalformedConfig(){
		try{
			Config config = new Config(CONFIG_PATH + "malformedConfig.properties");
		}catch(MalformedConfigFileException e){
			System.err.println(e.getMessage());
			return;
		}catch(FileNotFoundException e){
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void ConfigFileNotFound(){
		try{
			Config config = new Config(CONFIG_PATH + "notFound.properties");
		}catch(FileNotFoundException e){
			System.err.println(e.getMessage());
			return;
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}
