package com.fulinlin.utils;

import com.fulinlin.model.CommitTemplate;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.StringWriter;
import java.util.Properties;

/**
 * @author fulin
 */
public class VelocityUtils {

    private static VelocityEngine engine;


    static {
        engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.PARSER_POOL_SIZE, 20);
        engine.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        engine.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");

        Properties props = new Properties();
        props.put("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
        props.put("runtime.log.logsystem.log4j.category", "velocity");
        props.put("runtime.log.logsystem.log4j.logger", "velocity");

        engine.init(props);
    }

    public static String convert(String template, CommitTemplate commitTemplate) {
        StringWriter writer = new StringWriter();
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("author", commitTemplate.getAuthorName());
        velocityContext.put("card", commitTemplate.getCardNumber());
        velocityContext.put("type", commitTemplate.getType());
        velocityContext.put("subject", commitTemplate.getSubject());
        velocityContext.put("body", commitTemplate.getBody());
        velocityContext.put("newline", "\n");
        velocityContext.put("velocityTool", new VelocityTool());
        String vmLogTag = "Leetcode VelocityUtils";
        boolean isSuccess = engine.evaluate(velocityContext, writer, vmLogTag, template);
        return writer.toString();
    }
}
