package ar.fiuba.tecnicas.logger;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.OutputConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

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
    public void testExceptions(){
        try{
            Config config = TestUtils.buildConfig();
            Throwable exception = new NullPointerException("Prueba");

            PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);

            Logger logger = LoggerFactory.getLogger(SLF4J_Test.class);
            logger.trace(TestUtils.TEST_LINE,exception);
            logger.debug(TestUtils.TEST_LINE,exception);
            logger.info(TestUtils.TEST_LINE,exception);
            logger.warn(TestUtils.TEST_LINE,exception);
            logger.error(TestUtils.TEST_LINE,exception);

            String[] messages = new String[5];
            for (int i = 0; i <= 4; i++){
                messages[i] = TestUtils.TEST_LINE+": "+exception.getMessage();
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
}
