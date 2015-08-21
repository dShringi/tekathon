package util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class GenerateNotification {
	
	public static String generateNotification() throws IOException {
		Resource resource = new ClassPathResource("velocity.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		VelocityEngine ve = new VelocityEngine(props);
		ve.init();
		
		Template t = ve.getTemplate("templates/email.vm", "UTF-8");
		VelocityContext context = new VelocityContext();
		context.put("userName", "Dhawan");
		context.put("messageBody", "Message Body");
		context.put("yourName", "Mastek");
		
		StringWriter stringWriter = new StringWriter();
		t.merge(context, stringWriter);
		return stringWriter.toString();

	}

}
