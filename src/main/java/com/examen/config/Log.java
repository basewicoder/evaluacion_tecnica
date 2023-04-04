package com.examen.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class Log {
    final public static Logger logger = LoggerFactory.getLogger(Log.class);
    final private static String JWT = "%s -> Message: {} ";

    final public static String toJSON(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            return ex.getMessage();
        }
    }

    public static   <T extends Object> T jsonToClass(Class<T>  classs , Object data)  {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Log.log(" .::::: "+classs.getSimpleName()+" ::::: "+data);

        try {
            T parseBody = mapper.readValue(Log.toJSON(data), classs);
            return  parseBody;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Request failed body");
        }
    }
    final public static <T extends Object> T jsonToObject(String data, Class<T> entity) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            T ob = mapper.readValue(data, entity);
            return ob;
        } catch (Exception ex) {
            Log.log("Error when you try to convert String to Json " + ex.getMessage());
            return null;

        }
    }

    final public static <T extends Object> T jsonToObjectUnknowns(String data, Class<T> entity) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T ob = mapper.readValue(data, entity);
            return ob;
        } catch (Exception ex) {
            Log.log("Error when you try to convert String to Json " + ex.getMessage());
            return null;

        }
    }

    //<editor-fold defaultstate="collapsed" desc="Logger">

    final public static void log(Object obj,String message) {
        System.out.println("=============end===="+message+"==============");
        logger.info(Log.toJSON(obj));
        System.out.println("============find===="+message+"==============");
    }

    final public static void log(Object obj) {
        System.out.println("===================init============");
        logger.info(Log.toJSON(obj));
        System.out.println("===================end==========");
    }
    final public static void log(String obj,String message) {
        System.out.println("==========init===="+message+"===========");
        logger.info(obj);
        System.out.println("==========end===="+message+"============");
    }
    final public static void log(String message) {
        logger.info(message);
    }

    final public static void auth(String msg, Object exception) {
        logger.info(" ----------- JWT ----------- ");
        logger.info(String.format(JWT, msg), exception);
        logger.info(" ----------- END ----------- ");
    }

    final public static void log(String message, Object exception) {
        logger.info(message, exception);
    }

    final public static void log(String message, Object... exception) {
        logger.info(message, exception);
    }

    final public static void warn(String message) {
        logger.warn(message);
    }

    final public static void warn(String message, Object exception) {
        logger.warn(message, exception);
    }

    final public static void error(String message, Object exception) {
        logger.error(message, exception);
    }

    final public static void error(String message) {
        logger.error(message);
    }
    //</editor-fold>

}
