package ar.fiuba.tecnicas.logger;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.config.Config;

public class ConfigTest {

	@Test
	public void loadConfigTest(){
		Config config = 
				new Config("src/main/resources/default.properties");
		
		assertEquals(config.getFilter(), "DEBUG");
		assertEquals(config.getFormat(), "%d{HH:mm:ss}-%p-%t-%m");
		assertEquals(config.getSeparator(), "-");
		assertEquals(config.getFiles()[0], "file1");
		assertEquals(config.getFiles()[1], "file2");
		assertEquals(config.logOnConsole(), true);
		
	}
}
