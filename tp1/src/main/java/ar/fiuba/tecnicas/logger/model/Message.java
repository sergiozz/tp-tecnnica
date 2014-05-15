package ar.fiuba.tecnicas.logger.model;

import java.util.Date;

import ar.fiuba.tecnicas.logger.app.Level;

public class Message {
	private String userMessage;
    private Level level;
    private String methodName;
    private Date date;
	private String filename;
	private long threadId;

    public Message(String userMessage, Level level, String filename, String methodName){
    	this.userMessage = userMessage;
    	this.date = new Date();
    	this.level = level;
    	this.methodName = methodName;
    	this.filename = filename;
    }

	public String getMessage() {
		return this.userMessage;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}
	
	
}
