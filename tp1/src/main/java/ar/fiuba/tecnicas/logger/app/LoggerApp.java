package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.config.Config;

public class LoggerApp {
	public static void main (String [ ] args){
		System.out.println("Test");
		LoggerApp app = new LoggerApp();
		app.run(args);
	}
	
	private void run(String[] args){
		if (args.length < 2){
			System.out.println("Not enough parameters\n");
			return;
		}
		
		Config config = new Config(args[1]);
		Logger logger = new Logger(config);
		while(true){
			logger.log("");
		}
	}
}
