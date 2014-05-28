package ar.fiuba.tecnicas.logger;

import static org.junit.Assert.assertEquals;

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
		Config config = TestUtils.buildConfig();
		
		PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);
		
		Logger logger = new Logger(config);
        logger.debug(TestUtils.TEST_LINE);
        logger.warn(TestUtils.TEST_LINE);
        logger.error(TestUtils.TEST_LINE);
        logger.fatal(TestUtils.TEST_LINE);
        logger.off(TestUtils.TEST_LINE);
		logger.close();
		
		TestUtils.destroyFiles(TestUtils.CONSOLE_OUT_TEST_FILE);
		for (OutputConfig o : config.getOutputConfigs()){
			TestUtils.destroyFiles(o.getPath());
		}
		
		TestUtils.restoreStdOut(console);
	}
	
	@Test
	public void testLevel(){
		Logger logger = new Logger(TestUtils.buildConfig());
		assertEquals(Boolean.FALSE, logger.isDebug());
		assertEquals(Boolean.TRUE, logger.isInfo());
		assertEquals(Boolean.TRUE, logger.isWarning());
		assertEquals(Boolean.TRUE, logger.isError());
		assertEquals(Boolean.TRUE, logger.isFatal());
		
	}
}
