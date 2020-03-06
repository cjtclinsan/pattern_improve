package suanfa.decorator.demo3;

import jdk.internal.instrumentation.Logger;

/**
 * @author taosh
 * @create 2020-03-04 21:55
 */
public class LoggerDecorator implements Logger {

    public Logger logger;
    public LoggerDecorator(Logger logger){
        this.logger = logger;
    }

    @Override
    public void error(String s) {

    }

    @Override
    public void warn(String s) {

    }

    @Override
    public void info(String s) {

    }

    @Override
    public void debug(String s) {

    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void error(String s, Throwable throwable) {

    }
}
