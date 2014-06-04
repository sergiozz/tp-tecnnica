package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vizcopa on 03/06/2014.
 */
public class SLF4J_LoggerFactory implements ILoggerFactory  {
    private Map<String, SLF4J_LoggerAdapter> loggerMap;

    public SLF4J_LoggerFactory() {
        loggerMap = new HashMap<String, SLF4J_LoggerAdapter>();
    }

    @Override
    public Logger getLogger(String name) {
        synchronized (loggerMap) {
        	try{
	            if (!loggerMap.containsKey(name)) {
	                loggerMap.put(name, new SLF4J_LoggerAdapter(name));
	            }
	            return loggerMap.get(name);
        	}catch(MalformedConfigFileException e){
        		e.printStackTrace();
        		return null;
        	}
        }
    }

    
}
