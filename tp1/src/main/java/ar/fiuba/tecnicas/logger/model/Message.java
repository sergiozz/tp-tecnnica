package ar.fiuba.tecnicas.logger.model;

import java.util.Date;

/*
 * Responsabilities: Modela un mensaje de log
 * 
 * 
 * */

public class Message {
	private String userMessage;
    private Level level;
    private String methodName;
    private Date date;
	private String filename;
	private long threadId;
	private int line;
	private String loggerModule;

    public Message(String userMessage, Level level, String filename, String methodName, String loggerModule){
    	this.userMessage = userMessage;
    	this.date = new Date();
    	this.level = level;
    	this.methodName = methodName;
    	this.filename = filename;
    	this.loggerModule = loggerModule;
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

	public String getUserMessage() {
		return userMessage;
	}

	public Level getLevel() {
		return level;
	}

	public String getMethodName() {
		return methodName;
	}

	public Date getDate() {
		return date;
	}

	public String getFilename() {
		return filename;
	}

	public long getThreadId() {
		return threadId;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
	public int getLine(){
		return this.line;
	}

	public String getLoggerModule() {
		return this.loggerModule;
	}
}
