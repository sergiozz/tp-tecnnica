package ar.fiuba.tecnicas.logger.out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ar.fiuba.tecnicas.logger.formatter.MessageFormatter;
import ar.fiuba.tecnicas.logger.model.Message;

public class FileOutputAdapter extends OutputAdapter{
	
	private File file;
	private BufferedWriter writer;
	private String filename;
	
	public FileOutputAdapter(String filename, MessageFormatter formatter){
		super(formatter);
		this.filename = filename;		
	}
	
	public void write(Message msg){
		try{			
			this.writer.write(this.formatter.formatMessage(msg));
		}catch(IOException e){
			
		}
	}

	@Override
	public void open() {
		try{
			this.file = new File(filename);
		
			if (!file.exists()) {
				this.file.createNewFile();
			}
		
			FileWriter fw = new FileWriter(this.file.getAbsoluteFile());
			this.writer = new BufferedWriter(fw);
		}catch(IOException e){
			
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
