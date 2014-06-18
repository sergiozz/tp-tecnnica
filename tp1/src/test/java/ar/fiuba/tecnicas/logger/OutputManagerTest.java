package ar.fiuba.tecnicas.logger;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.formatter.TextMessageFormatter;
import ar.fiuba.tecnicas.logger.model.Level;
import ar.fiuba.tecnicas.logger.model.Message;
import ar.fiuba.tecnicas.logger.out.ConsoleOutputAdapter;
import ar.fiuba.tecnicas.logger.out.FileOutputAdapter;
import ar.fiuba.tecnicas.logger.out.OutputManager;
import ar.fiuba.tecnicas.logger.outputFactory.OutputFactory;

public class OutputManagerTest {

	private static final String TEST_FILENAME = "log";
	
	
	@Test
	public void fileOutputTest(){
		try{
			Config config = TestUtils.buildConfig();
			
			FileOutputAdapter out = (FileOutputAdapter)OutputFactory.createOutput(config, config.getOutputConfigs().get(1), config.getFormat());
			out.open();
			Message message = TestUtils.builMessage();
			String[] messages = {config.getFormat().getMessageFormatter().formatMessage(message)};
			
			out.write(message);
			out.close();
			
			TestUtils.testFileContentsWithDates(TEST_FILENAME, messages);
		}catch(Exception e ){
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void consoleOutputTest(){
		try{
			PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);
			Config config = TestUtils.buildConfig();
			
			ConsoleOutputAdapter out = (ConsoleOutputAdapter)OutputFactory.createOutput(config, config.getOutputConfigs().get(0), config.getFormat());
			out.open();
			Message message = TestUtils.builMessage();
			String[] messages = {config.getFormat().getMessageFormatter().formatMessage(message)};
			
			out.write(message);
			out.close();
			
			TestUtils.testFileContentsWithDates(TestUtils.CONSOLE_OUT_TEST_FILE, messages);
			
			TestUtils.restoreStdOut(console);
		}catch(Exception e ){
			System.err.println(e.getMessage());
		}
		
	}
	
	

	@Test
	public void outputManagerTest(){
		try{
			PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);
			Config config = TestUtils.buildConfig();
			
			OutputManager manager = new OutputManager(config);
			Message message = TestUtils.builMessage(TestUtils.TEST_LINE, Level.WARN);
			
			String[] messages = {config.getFormat().getMessageFormatter().formatMessage(message)};
			
			manager.write(message);
			
			manager.shutdown();
			
			TestUtils.testFileContentsWithDates(TestUtils.CONSOLE_OUT_TEST_FILE, messages);
			for ( OutputConfig o : config.getOutputConfigs()){
				if (o.getValueForKey("filename") != null && !o.getValueForKey("filename").equalsIgnoreCase("console")){
					TestUtils.testFileContentsWithDates(o.getValueForKey("filename"), messages);
				}
			}
			
			TestUtils.restoreStdOut(console);
		}catch(Exception e ){
			System.err.println(e.getMessage());
		}
		
	}
		
}
