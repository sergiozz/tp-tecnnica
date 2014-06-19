package ar.fiuba.tecnicas.logger.app;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsabilities: Factory del Logger, devuelve instancias de estas segun su nombre asociado.
 */
public class LoggerFactory {
    private Map<String, Logger> loggerMap;

    public LoggerFactory() {
        loggerMap = new HashMap<String, Logger>();
    }

    public Logger getLogger(String name) {
        synchronized (loggerMap) {
            try{
                if (!loggerMap.containsKey(name)) {
                    loggerMap.put(name, new Logger(name));
                }
                return loggerMap.get(name);
            }catch(MalformedConfigFileException e){
                e.printStackTrace();
                return null;
            }
        }
    }
}
