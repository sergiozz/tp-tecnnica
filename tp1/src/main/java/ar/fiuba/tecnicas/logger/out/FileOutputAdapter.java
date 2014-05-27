package ar.fiuba.tecnicas.logger.out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Responsabilities: Escribe un objeto Message a un archivo
 * 
 * 
 * */


import ar.fiuba.tecnicas.logger.config.OutputConfig;
import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;

public class FileOutputAdapter extends OutputAdapter{
	
	private File file;
	private BufferedWriter writer;
	
	public FileOutputAdapter(MessageFormatter formatter, OutputConfig outputConfig){
		super(formatter, outputConfig);
	}
	
	public void write(Message msg){
		try{
			if (msg.getLevel().getValue() <= this.outputConfig.getFilter().getValue()){
				this.writer.write(this.formatter.formatMessage(msg) + "\n");
			}
		}catch(IOException e){
			
		}
	}

	@Override
	public void open() {
		try{
			this.file = new File(this.outputConfig.getPath());
		
			if (!file.exists()) {
				this.file.createNewFile();
			}
		
			FileWriter fw = new FileWriter(this.file.getAbsoluteFile(), true);
			this.writer = new BufferedWriter(fw);
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void close() {
		try{
			this.writer.close();
		}catch(IOException e){
			
		}
	}
}
