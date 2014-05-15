package ar.fiuba.tecnicas.logger;

import java.io.PrintStream;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;
import ar.fiuba.tecnicas.logger.out.ConsoleOutputAdapter;
import ar.fiuba.tecnicas.logger.out.FileOutputAdapter;
import ar.fiuba.tecnicas.logger.out.OutputManager;

public class OutputManagerTest {

	private static final String TEST_FILENAME = "tempTestFile";
	
	
	@Test
	public void fileOutputTest(){
		Config config = TestUtils.buildConfig();
		MessageFormatter formatter = TestUtils.buildFormatter(config);
		FileOutputAdapter out = new FileOutputAdapter(TEST_FILENAME, formatter);
		out.open();
		Message message = TestUtils.builMessage();
		
		out.write(message);
		out.close();
		
		TestUtils.testFileContents(TEST_FILENAME);
	}
	
	@Test
	public void consoleOutputTest(){
		Config config = TestUtils.buildConfig();
		MessageFormatter formatter = TestUtils.buildFormatter(config);
		PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);
		
		ConsoleOutputAdapter out = new ConsoleOutputAdapter(formatter);
		Message message = TestUtils.builMessage();
		
		out.write(message);
		out.close();
		
		TestUtils.testFileContents(TestUtils.CONSOLE_OUT_TEST_FILE);
		
		TestUtils.restoreStdOut(console);
	}
	
	

	@Test
	public void outputManagerTest(){
		PrintStream console = TestUtils.redirectStdOut(TestUtils.CONSOLE_OUT_TEST_FILE);
		Config config = TestUtils.buildConfig();
		OutputManager manager = new OutputManager(config);
		
		Message message = TestUtils.builMessage();
		
		manager.write(message);
		
		manager.shutdown();
		
		TestUtils.testFileContents(TestUtils.CONSOLE_OUT_TEST_FILE);
		for ( String f : config.getFiles()){
			TestUtils.testFileContents(f);
		}
		
		TestUtils.restoreStdOut(console);
	}
}
