package suanfa.decorator.demo3;

import com.alibaba.fastjson.JSONObject;
import jdk.internal.instrumentation.Logger;

import java.util.Arrays;

/**
 * @author taosh
 * @create 2020-03-06 11:48
 */
public class JsonLogger extends LoggerDecorator {
    public JsonLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void error(String s) {
        JSONObject result = composeBasicJsonResult();
        result.put("ERROR", s);
        logger.error(result.toString());
    }

    @Override
    public void info(String s) {
        JSONObject result = composeBasicJsonResult();
        result.put("MESSAGE", s);
        logger.info(result.toString());
    }

    public void error(Exception e){
        JSONObject result = composeBasicJsonResult();
        result.put("EXCEPTION", e.getClass().getName());
        String exceptionStackTrace = Arrays.toString(e.getStackTrace());
        result.put("STACKTRACE", exceptionStackTrace);
        logger.error(result.toString());
    }

    private JSONObject composeBasicJsonResult(){
        //拼装信息
        return new JSONObject();
    }
}
