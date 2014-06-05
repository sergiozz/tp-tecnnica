package ar.fiuba.tecnicas.logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.PrintStream;

import ar.fiuba.tecnicas.logger.app.LoggerFactory;
import org.junit.Test;

import ar.fiuba.tecnicas.logger.app.Logger;
import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.OutputConfig;

public class LoggerTest {

    public static final String MODULE_ONE = "Module One";
    public static final String MODULE_TWO = "Module Two";
    public static final String MODULE_THREE = "Module Three";

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
			assertEquals(Boolean.FALSE, logger.isTrace());
			assertEquals(Boolean.TRUE, logger.isDebug());
			assertEquals(Boolean.TRUE, logger.isInfo());
			assertEquals(Boolean.TRUE, logger.isWarning());
			assertEquals(Boolean.TRUE, logger.isError());
			assertEquals(Boolean.TRUE, logger.isFatal());
		}catch(Exception e ){
			System.err.println(e.getMessage());
		}
	}

    @Test
    public void testModules(){
        try {
            LoggerFactory loggerFactory = new LoggerFactory();
            Logger logger1 = loggerFactory.getLogger(MODULE_ONE);
            Logger logger2 = loggerFactory.getLogger(MODULE_TWO);
            Logger logger3 = loggerFactory.getLogger(MODULE_THREE);

            assertFalse(logger2.getName().contains(MODULE_ONE));
            assertFalse(logger3.getName().contains(MODULE_TWO));
            assertFalse(logger1.getName().contains(MODULE_THREE));

            assertTrue(logger1.getName().contains(MODULE_ONE));
            assertTrue(logger2.getName().contains(MODULE_TWO));
            assertTrue(logger3.getName().contains(MODULE_THREE));
        }catch(Exception e ){
            System.err.println(e.getMessage());
        }
    }
}
