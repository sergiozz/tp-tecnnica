package ar.fiuba.tecnicas.logger.model;

import java.util.Date;

public class Message {
	private String processMessage;
    private String filter;
    private String originalMessage;
    private String methodName;
    private Date date;

    public Message(String originalMessage){
        this.originalMessage=originalMessage;
    }

    public Message(String originalMessage, String filter){
        this.originalMessage=originalMessage;
        this.filter=filter;
    }

    public Message(String originalMessage, String filter, String methodName){
        this.originalMessage=originalMessage;
        this.filter=filter;
        this.methodName=methodName;
    }

    public Message(String originalMessage, String filter, String methodName, Date date){
        this.originalMessage=originalMessage;
        this.filter=filter;
        this.methodName=methodName;
        this.date=date;
    }

	public String getMessage() {
		return processMessage;
	}

	public void setMessage(String processMessage) {
		this.processMessage = processMessage;
	}

    public String getFilter() {
        return filter;
    }

    public String getDate() {
        return date.toString();
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public String getMethodName() {
        return methodName;
    }
}
