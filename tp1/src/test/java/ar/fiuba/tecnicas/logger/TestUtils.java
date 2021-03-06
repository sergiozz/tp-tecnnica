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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ar.fiuba.tecnicas.logger.config.Config;
import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.formatter.TextMessageFormatter;
import ar.fiuba.tecnicas.logger.model.Level;
import ar.fiuba.tecnicas.logger.model.Message;

public class TestUtils {
	
	public static final String TEST_LINE = "testLine";
	public static final String TEST_CONFIG = "resources/logger-config.properties";
	public static final String TEST_FILENAME = "TestClassName";
	public static final String TEST_METHOD_NAME = "TestMethodName";
	public static final String CONSOLE_OUT_TEST_FILE = "tempConsoleOutFile";
	private static final String TEST_LOGGER_MODULE = "TestLoggerModule";
	
	public static Config buildConfig() throws FileNotFoundException, MalformedConfigFileException{
		Config config = new Config(TEST_CONFIG);
		
		return config;
	}
	
	public static Config buildConfig(String configFilename) throws FileNotFoundException, MalformedConfigFileException{
		Config config = new Config(configFilename);
		
		return config;
	}
	
	public static Message builMessage(){
		return TestUtils.builMessage(TEST_LINE);
	}
	
	public static Message builMessage(String userMessage){
		return TestUtils.builMessage(userMessage, Level.DEBUG);
	}
	
	
	public static Message builMessage(String userMessage, Level level){
		Message message = new Message(userMessage, level, TEST_FILENAME, TEST_METHOD_NAME, TEST_LOGGER_MODULE);
		message.setDate(new Date());
		message.setThreadId(Thread.currentThread().getId());
		return message;
	}
	
	public static void testFileContentsWithDates(String filename, String[] messages) {
		try{
			FileInputStream testFileStream = new FileInputStream(filename);
		
			DataInputStream in = new DataInputStream(testFileStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();
			int i = 0;
			while (line != null){
				assertEquals(line, messages[i]);
				line = br.readLine();
				i++;
			}
			
			br.close();
			
			TestUtils.destroyFiles(filename);
			
		}catch(IOException e){
			System.err.println(e.getMessage());
		}		
	}
	
	public static void testFileContents(String filename, String[] messages, int level) {
		try{
			FileInputStream testFileStream = new FileInputStream(filename);
		
			DataInputStream in = new DataInputStream(testFileStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();
			int i = messages.length - level;
			while (line != null){
				String regex = "(.*)"+messages[i]+"(.*)";
				assertEquals(Boolean.TRUE, line.matches(regex));
				line = br.readLine();
				i++;
			}
			
			br.close();
			
			TestUtils.destroyFiles(filename);
			
		}catch(IOException e){
			System.err.println(e.getMessage());
		}		
	}

	public static void destroyFiles(String filename){
		if (filename == null){
			return;
		}
		File testFile = new File(filename);
		if (testFile.exists()){
			testFile.delete();
		}   
		
	}
	
	public static PrintStream redirectStdOut(String filename) {
		PrintStream console = System.out;

		try{
			PrintStream ps = new PrintStream(new FileOutputStream(new File(filename)));
			System.setOut(ps);
		}catch(FileNotFoundException e){
			System.err.println(e.getMessage());
		}
		return console;
	}
	
	public static void restoreStdOut(PrintStream console){
		System.setOut(console);
		
	}

	public static TextMessageFormatter buildTextFormatter(Config config) {
		return new TextMessageFormatter(config.getFormat());
	}

	public static void testFileContentsForRegexFilter(String filename,
			String[] messages, String regex, int level) {
		List<String> unfilteredMessages = new LinkedList<String>();
		for(String m : messages){
			if (!m.matches(regex)){
				unfilteredMessages.add(m);
			}
			
		}
		
		try{
			FileInputStream testFileStream = new FileInputStream(filename);
		
			DataInputStream in = new DataInputStream(testFileStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();
			int i = messages.length - level;
			while (line != null){
				String regexMessage = "(.*)"+messages[i]+"(.*)";
				assertEquals(Boolean.TRUE, line.matches(regexMessage));
				line = br.readLine();
				i++;
			}
			
			br.close();
			
			TestUtils.destroyFiles(filename);
			
		}catch(IOException e){
			System.err.println(e.getMessage());
		}		
		
	}
}
