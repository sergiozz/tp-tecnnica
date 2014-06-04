package ar.fiuba.tecnicas.logger;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.app.Logger;
import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Level;

public class LoggerTest {

	@Test
	public void testLogger(){
		try{
			Config config = TestUtils.buildConfig();
		
			PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);
		
			Logger logger = new Logger();
			logger.trace(TestUtils.TEST_LINE);
			logger.debug(TestUtils.TEST_LINE);
			logger.info(TestUtils.TEST_LINE);
			logger.warn(TestUtils.TEST_LINE);
			logger.error(TestUtils.TEST_LINE);
			logger.fatal(TestUtils.TEST_LINE);
			logger.close();
		
			String[] messages = new String[5]; 
			for (int i = 0; i <= 4; i++){
				messages[i] = TestUtils.TEST_LINE;
			}
			
			for (OutputConfig o : config.getOutputConfigs()){
				int level = (o.getFilter().getValue() > config.getLevel().getValue())?
						(config.getLevel().getValue()):
						(o.getFilter().getValue());	
				if (o.getPath() != null){
					TestUtils.testFileContents(o.getPath(), messages, level);
					TestUtils.destroyFiles(o.getPath());
				}else{
					TestUtils.testFileContents(TestUtils.CONSOLE_OUT_TEST_FILE, messages, level);
					TestUtils.destroyFiles(TestUtils.CONSOLE_OUT_TEST_FILE);
				}
			}
		
			TestUtils.restoreStdOut(console);
		}catch(Exception e ){
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void testLevel(){
		try{
			Logger logger = new Logger();
            logger.setConfig(TestUtils.buildConfig());
			assertEquals(Boolean.FALSE, logger.isTrace());
			assertEquals(Boolean.FALSE, logger.isDebug());
			assertEquals(Boolean.TRUE, logger.isInfo());
			assertEquals(Boolean.TRUE, logger.isWarning());
			assertEquals(Boolean.TRUE, logger.isError());
			assertEquals(Boolean.TRUE, logger.isFatal());
		}catch(Exception e ){
			System.err.println(e.getMessage());
		}
		
	}
}
