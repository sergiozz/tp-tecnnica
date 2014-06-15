package org.slf4j.impl;

import ar.fiuba.tecnicas.logger.app.LoggerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vizcopa on 03/06/2014.
 */
public class SLF4J_LoggerFactory implements ILoggerFactory  {

    @Override
    public Logger getLogger(String name) {
            return new SLF4J_LoggerAdapter(name);
    }
}
