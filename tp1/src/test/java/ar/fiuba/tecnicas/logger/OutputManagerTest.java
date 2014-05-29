package ar.fiuba.tecnicas.logger;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.config.OutputType;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Level;
import ar.fiuba.tecnicas.logger.model.Message;
import ar.fiuba.tecnicas.logger.out.ConsoleOutputAdapter;
import ar.fiuba.tecnicas.logger.out.FileOutputAdapter;
import ar.fiuba.tecnicas.logger.out.OutputManager;

public class OutputManagerTest {

	private static final String TEST_FILENAME = "tempTestFile";
	
	
	@Test
	public void fileOutputTest(){
		try{
			Config config = TestUtils.buildConfig();
			MessageFormatter formatter = TestUtils.buildFormatter(config);
			OutputConfig fileOutputConfig = new OutputConfig(Level.DEBUG, TEST_FILENAME, OutputType.FILE);
			
			FileOutputAdapter out = new FileOutputAdapter(formatter, fileOutputConfig);
			out.open();
			Message message = TestUtils.builMessage();
			String[] messages = {formatter.formatMessage(message)};
			
			out.write(message);
			out.close();
			
			TestUtils.testFileContents(TEST_FILENAME, messages);
		}catch(Exception e ){
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void consoleOutputTest(){
		try{
			Config config = TestUtils.buildConfig();
			MessageFormatter formatter = TestUtils.buildFormatter(config);
			PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);
			OutputConfig consoleOutputConfig = new OutputConfig(Level.DEBUG, 
					TestUtils.CONSOLE_OUT_TEST_FILE, OutputType.CONSOLE);
			
			ConsoleOutputAdapter out = new ConsoleOutputAdapter(formatter, consoleOutputConfig);
			Message message = TestUtils.builMessage();
			String[] messages = {formatter.formatMessage(message)};
			
			out.write(message);
			out.close();
			
			TestUtils.testFileContents(TestUtils.CONSOLE_OUT_TEST_FILE, messages);
			
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
			MessageFormatter formatter = TestUtils.buildFormatter(config);
			OutputManager manager = new OutputManager(config);
			Message message = TestUtils.builMessage(TestUtils.TEST_LINE, Level.WARN);
			
			String[] messages = {formatter.formatMessage(message)};
			
			manager.write(message);
			
			manager.shutdown();
			
			TestUtils.testFileContents(TestUtils.CONSOLE_OUT_TEST_FILE, messages);
			for ( OutputConfig o : config.getOutputConfigs()){
				//if null then it is a console output
				if (o.getPath() != null){
					TestUtils.testFileContents(o.getPath(), messages);
				}
			}
			
			TestUtils.restoreStdOut(console);
		}catch(Exception e ){
			System.err.println(e.getMessage());
		}
		
	}
		
}
