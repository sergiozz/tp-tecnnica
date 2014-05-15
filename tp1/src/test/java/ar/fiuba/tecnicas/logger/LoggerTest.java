package ar.fiuba.tecnicas.logger;

import java.io.PrintStream;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.app.Level;
import ar.fiuba.tecnicas.logger.app.Logger;
import ar.fiuba.tecnicas.logger.config.Config;

public class LoggerTest {

	@Test
	public void testLogger(){
		Config config = TestUtils.buildConfig();
		
		PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);
		
		Logger logger = new Logger(config);
		logger.log(TestUtils.TEST_LINE);
		logger.log(TestUtils.TEST_LINE, Level.INFO);
		logger.log(TestUtils.TEST_LINE, Level.DEBUG);
		logger.log(TestUtils.TEST_LINE, Level.ERROR, TestUtils.TEST_FILENAME, TestUtils.TEST_METHOD_NAME);
		logger.close();
		
				
		
		TestUtils.restoreStdOut(console);
	}
}
