package ar.fiuba.tecnicas.logger;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.model.Level;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vizcopa on 04/06/2014.
 */
public class SLF4J_Test {


    public static final String MODULE_ONE = "Module One";
    public static final String MODULE_TWO = "Module Two";
    public static final String MODULE_THREE = "Module Three";

    @Test
    public void testLogger(){
        try{
            Config config = TestUtils.buildConfig();

            PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);

            Logger logger = LoggerFactory.getLogger(SLF4J_Test.class);
            logger.trace(TestUtils.TEST_LINE);
            logger.debug(TestUtils.TEST_LINE);
            logger.info(TestUtils.TEST_LINE);
            logger.warn(TestUtils.TEST_LINE);
            logger.error(TestUtils.TEST_LINE);

            String[] messages = new String[5];
            for (int i = 0; i <= 4; i++){
                messages[i] = TestUtils.TEST_LINE;
            }

            OutputConfig o = config.getOutputConfigs().get(0);
            TestUtils.testFileContents(TestUtils.CONSOLE_OUT_TEST_FILE, messages,
                    (Level.valueOf(o.getValueForKey("filter_data")).getValue()));
            TestUtils.destroyFiles(TestUtils.CONSOLE_OUT_TEST_FILE);

            o = config.getOutputConfigs().get(1);

            int filterLevel = Level.valueOf(o.getValueForKey("filter_data")).getValue();
            int level = (filterLevel > config.getLevel().getValue())?
                    (config.getLevel().getValue()):
                    (filterLevel);
            TestUtils.testFileContents(o.getValueForKey("filename"),
                    messages,
                    level);
            TestUtils.destroyFiles(o.getValueForKey("filename"));

            o = config.getOutputConfigs().get(2);

            String regex = o.getValueForKey("filter_data");
            TestUtils.testFileContentsForRegexFilter(o.getValueForKey("filename"),
                    messages, regex, config.getLevel().getValue());
            TestUtils.destroyFiles(o.getValueForKey("filename"));

            TestUtils.restoreStdOut(console);
        }catch(Exception e ){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testLevel(){
        try {
            Logger logger = LoggerFactory.getLogger(SLF4J_Test.class);

            assertEquals(Boolean.FALSE, logger.isTraceEnabled());
            assertEquals(Boolean.TRUE, logger.isDebugEnabled());
            assertEquals(Boolean.TRUE, logger.isInfoEnabled());
            assertEquals(Boolean.TRUE, logger.isWarnEnabled());
            assertEquals(Boolean.TRUE, logger.isErrorEnabled());
        }catch(Exception e ){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testModules(){
        try {
            Logger logger1 = LoggerFactory.getLogger(MODULE_ONE);
            Logger logger2 = LoggerFactory.getLogger(MODULE_TWO);
            Logger logger3 = LoggerFactory.getLogger(MODULE_THREE);

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

    @Test
    public void testOthersFormsLogger(){
        try{
            Config config = TestUtils.buildConfig();

            PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);

            Logger logger = LoggerFactory.getLogger(SLF4J_Test.class);
            logger.trace("Prueba {} de parametros","x");
            logger.debug("Prueba {} {} parametros","x","de");
            logger.info("Prueba {} de parametros","x");
            logger.warn("Prueba {} {} parametros","x","de");
            logger.error("Prueba {} {} {}","x","de","parametros");

            String[] messages = new String[5];
            for (int i = 0; i <= 4; i++){
                messages[i] = "Prueba x de parametros";
            }
            
            OutputConfig o = config.getOutputConfigs().get(0);
			TestUtils.testFileContents(TestUtils.CONSOLE_OUT_TEST_FILE, messages, 
						(Level.valueOf(o.getValueForKey("filter_data")).getValue()));
			TestUtils.destroyFiles(TestUtils.CONSOLE_OUT_TEST_FILE);
			
			o = config.getOutputConfigs().get(1);
			
			int filterLevel = Level.valueOf(o.getValueForKey("filter_data")).getValue();
			int level = (filterLevel > config.getLevel().getValue())?
					(config.getLevel().getValue()):
						(filterLevel);	
			TestUtils.testFileContents(o.getValueForKey("filename"), 
						messages, 
						level);
			TestUtils.destroyFiles(o.getValueForKey("filename"));
			
			o = config.getOutputConfigs().get(2);
			
			String regex = o.getValueForKey("filter_data");
			TestUtils.testFileContentsForRegexFilter(o.getValueForKey("filename"), 
						messages, regex, config.getLevel().getValue());
			TestUtils.destroyFiles(o.getValueForKey("filename"));
			
		
			TestUtils.restoreStdOut(console);
        }catch(Exception e ){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testExceptions(){
        try{
            Config config = TestUtils.buildConfig();
            Throwable exception = new NullPointerException("Prueba");

            PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);

            Logger logger = LoggerFactory.getLogger(SLF4J_Test.class);
            logger.trace(TestUtils.TEST_LINE,exception);
            logger.debug(TestUtils.TEST_LINE, exception);
            logger.info(TestUtils.TEST_LINE, exception);
            logger.warn(TestUtils.TEST_LINE, exception);
            logger.error(TestUtils.TEST_LINE,exception);

            String[] messages = new String[5];
            for (int i = 0; i <= 4; i++){
                messages[i] = TestUtils.TEST_LINE+": "+exception.getMessage();
            }

            OutputConfig o = config.getOutputConfigs().get(0);
            TestUtils.testFileContents(TestUtils.CONSOLE_OUT_TEST_FILE, messages,
                    (Level.valueOf(o.getValueForKey("filter_data")).getValue()));
            TestUtils.destroyFiles(TestUtils.CONSOLE_OUT_TEST_FILE);

            o = config.getOutputConfigs().get(1);

            int filterLevel = Level.valueOf(o.getValueForKey("filter_data")).getValue();
            int level = (filterLevel > config.getLevel().getValue())?
                    (config.getLevel().getValue()):
                    (filterLevel);
            TestUtils.testFileContents(o.getValueForKey("filename"),
                    messages,
                    level);
            TestUtils.destroyFiles(o.getValueForKey("filename"));

            o = config.getOutputConfigs().get(2);

            String regex = o.getValueForKey("filter_data");
            TestUtils.testFileContentsForRegexFilter(o.getValueForKey("filename"),
                    messages, regex, config.getLevel().getValue());
            TestUtils.destroyFiles(o.getValueForKey("filename"));

            TestUtils.restoreStdOut(console);
        }catch(Exception e ){
            System.err.println(e.getMessage());
        }
    }
}
