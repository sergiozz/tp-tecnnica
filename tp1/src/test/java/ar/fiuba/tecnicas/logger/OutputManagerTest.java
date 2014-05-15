package ar.fiuba.tecnicas.logger;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Test;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.model.Message;
import ar.fiuba.tecnicas.logger.out.ConsoleOutputAdapter;
import ar.fiuba.tecnicas.logger.out.FileOutputAdapter;
import ar.fiuba.tecnicas.logger.out.OutputManager;

public class OutputManagerTest {

	private static final String TEST_FILENAME = "tempTestFile";
	private static final String CONSOLE_OUT_TEST_FILE = "tempConsoleOurFile";
	
	@Test
	public void fileOutputTest(){
		FileOutputAdapter out = new FileOutputAdapter(TEST_FILENAME);
		out.open();
		Message message = TestUtils.builMessage();
		
		out.write(message);
		out.close();
		
		TestUtils.testFileContents(TEST_FILENAME);
	}
	
	@Test
	public void consoleOutputTest(){
		PrintStream console = TestUtils.redirectStdOut(CONSOLE_OUT_TEST_FILE);
		
		ConsoleOutputAdapter out = new ConsoleOutputAdapter();
		Message message = TestUtils.builMessage();
		
		out.write(message);
		out.close();
		
		TestUtils.testFileContents(CONSOLE_OUT_TEST_FILE);
		
		TestUtils.restoreStdOut(console);
	}
	
	

	@Test
	public void outputManagerTest(){
		PrintStream console = TestUtils.redirectStdOut(CONSOLE_OUT_TEST_FILE);
		Config config = TestUtils.buildConfig();
		OutputManager manager = new OutputManager(config);
		
		Message message = TestUtils.builMessage();
		
		manager.write(message);
		
		manager.shutdown();
		
		TestUtils.testFileContents(CONSOLE_OUT_TEST_FILE);
		for ( String f : config.getFiles()){
			TestUtils.testFileContents(f);
		}
		
		TestUtils.restoreStdOut(console);
	}
}
