package ar.fiuba.tecnicas.logger;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Level;

public class ConfigTest {

	@Test
	public void loadConfigTest(){
		Config config = 
				new Config("src/main/resources/default.properties");
		
		assertEquals(config.getLevel(), Level.DEBUG);
		assertEquals(config.getFormat(), "%d{HH:mm:ss}-%p-%t-%m");
		assertEquals(config.getSeparator(), "-");
		assertEquals(config.getOutputConfigs().get(0).getPath(), null);
		assertEquals(config.getOutputConfigs().get(1).getPath(), "file1");
		assertEquals(config.getOutputConfigs().get(2).getPath(), "file2");
		
		
	}
}
