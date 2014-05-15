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

import ar.fiuba.tecnicas.logger.config.Config;

public class TestUtils {
	
	public static final String TEST_LINE = "testLine";
	public static final String TEST_CONFIG = "src/main/resources/testConfig.properties";

	public static Config buildConfig(){
		Config config = new Config(TEST_CONFIG);
		
		return config;
	}
	
	public static void testFileContents(String filename) {
		try{
			FileInputStream testFileStream = new FileInputStream(filename);
		
			DataInputStream in = new DataInputStream(testFileStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();
			br.close();
			
			File testFile = new File(filename);
	          if (testFile.exists()){
	        	  testFile.delete();
	          }   
			
			assertEquals(line, TEST_LINE);
		}catch(IOException e){
			System.err.println(e.getMessage());
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
}
