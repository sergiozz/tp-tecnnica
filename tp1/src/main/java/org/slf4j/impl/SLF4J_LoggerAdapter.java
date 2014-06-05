package org.slf4j.impl;

import org.slf4j.Logger;
import org.slf4j.Marker;

import java.text.MessageFormat;

/**
 * Created by Vizcopa on 03/06/2014.
 */
public class SLF4J_LoggerAdapter implements Logger {

    private ar.fiuba.tecnicas.logger.app.Logger loggerApi;
    private final static ar.fiuba.tecnicas.logger.app.LoggerFactory loggerFactoryApi =
            new ar.fiuba.tecnicas.logger.app.LoggerFactory();

    public SLF4J_LoggerAdapter(String name) {
        this.loggerApi = loggerFactoryApi.getLogger(name);
    }

    @Override
    public String getName() {
        return loggerApi.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return loggerApi.isTrace();
    }

    @Override
    public void trace(String s) {
        loggerApi.trace(s);
    }

    @Override
    public void trace(String s, Object o) {
        loggerApi.trace(MessageFormat.format(s, o));
    }

    @Override
    public void trace(String s, Object o, Object o2) {
        loggerApi.trace(MessageFormat.format(s, o, o2));
    }

    @Override
    public void trace(String s, Object... objects) {
        loggerApi.trace(MessageFormat.format(s, objects));
    }

    @Override
    public void trace(String s, Throwable throwable) {
        loggerApi.trace(s, throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return isTraceEnabled();
    }

    @Override
    public void trace(Marker marker, String s) {
        trace(s);
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        trace( s, o);
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o2) {
        trace( s, o, o2);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        trace( s, objects);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        trace( s, throwable );
    }

    @Override
    public boolean isDebugEnabled() {
        return loggerApi.isDebug();
    }

    @Override
    public void debug(String s) {
        loggerApi.debug(s);
    }

    @Override
    public void debug(String s, Object o) {
        loggerApi.debug(MessageFormat.format(s, o));
    }

    @Override
    public void debug(String s, Object o, Object o2) {
        loggerApi.debug(MessageFormat.format(s, o, o2));
    }

    @Override
    public void debug(String s, Object... objects) {
        loggerApi.debug(MessageFormat.format(s, objects));
    }

    @Override
    public void debug(String s, Throwable throwable) {
        loggerApi.debug(s, throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return isDebugEnabled();
    }

    @Override
    public void debug(Marker marker, String s) {
        debug(s);
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        debug( s, o);
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o2) {
        debug( s, o, o2);
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        debug( s, objects);
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        debug( s, throwable );
    }

    @Override
    public boolean isInfoEnabled() {
        return loggerApi.isInfo();
    }

    @Override
    public void info(String s) {
        loggerApi.info(s);
    }

    @Override
    public void info(String s, Object o) {
        loggerApi.info(MessageFormat.format(s, o));
    }

    @Override
    public void info(String s, Object o, Object o2) {
        loggerApi.info(MessageFormat.format(s, o, o2));
    }

    @Override
    public void info(String s, Object... objects) {
        loggerApi.info(MessageFormat.format(s, objects));
    }

    @Override
    public void info(String s, Throwable throwable) {
        loggerApi.info(s, throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return isInfoEnabled();
    }

    @Override
    public void info(Marker marker, String s) {
        info(s);
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        info( s, o);
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o2) {
        info( s, o, o2);
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        info( s, objects);
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        info( s, throwable );
    }

    @Override
    public boolean isWarnEnabled() {
        return loggerApi.isWarning();
    }

    @Override
    public void warn(String s) {
        loggerApi.warn(s);
    }

    @Override
    public void warn(String s, Object o) {
        loggerApi.warn(MessageFormat.format(s, o));
    }

    @Override
    public void warn(String s, Object o, Object o2) {
        loggerApi.warn(MessageFormat.format(s, o, o2));
    }

    @Override
    public void warn(String s, Object... objects) {
        loggerApi.warn(MessageFormat.format(s, objects));
    }

    @Override
    public void warn(String s, Throwable throwable) {
        loggerApi.warn(s, throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return isWarnEnabled();
    }

    @Override
    public void warn(Marker marker, String s) {
        warn(s);
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        warn( s, o);
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o2) {
        warn( s, o, o2);
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        warn( s, objects);
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        warn( s, throwable );
    }

    @Override
    public boolean isErrorEnabled() {
        return loggerApi.isError();
    }

    @Override
    public void error(String s) {
        loggerApi.error(s);
    }

    @Override
    public void error(String s, Object o) {
        loggerApi.error(MessageFormat.format(s, o));
    }

    @Override
    public void error(String s, Object o, Object o2) {
        loggerApi.error(MessageFormat.format(s, o, o2));
    }

    @Override
    public void error(String s, Object... objects) {
        loggerApi.error(MessageFormat.format(s, objects));
    }

    @Override
    public void error(String s, Throwable throwable) {
        loggerApi.error(s, throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return isErrorEnabled();
    }

    @Override
    public void error(Marker marker, String s) {
        error(s);
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        error( s, o);
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o2) {
        error( s, o, o2);
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        error( s, objects);
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        error( s, throwable );
    }
}
