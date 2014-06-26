package ar.fiuba.tecnicas.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: fdv
 * Date: 26/06/14
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String [ ] args)
    {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.error("test line");
    }
}
