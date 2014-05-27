package ar.fiuba.tecnicas.logger;

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
		logger.log(TestUtils.TEST_LINE);
		logger.log(TestUtils.TEST_LINE, Level.INFO);
		logger.log(TestUtils.TEST_LINE, Level.DEBUG);
		logger.log(TestUtils.TEST_LINE, Level.ERROR, TestUtils.TEST_FILENAME, TestUtils.TEST_METHOD_NAME);
		logger.close();
		
		TestUtils.destroyFiles(TestUtils.CONSOLE_OUT_TEST_FILE);
		for (OutputConfig o : config.getOutputConfigs()){
			TestUtils.destroyFiles(o.getPath());
		}
		
		TestUtils.restoreStdOut(console);
	}
}
