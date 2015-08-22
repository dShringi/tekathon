package util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class GenerateNotification {
	private Map<String, String> dataAttributes = new HashMap<String, String>();
	private Map<String, String> custAttributes = new HashMap<String, String>();

	public Map<String, String> getDataAttributes() {
		return dataAttributes;
	}

	public void setDataAttributes(Map<String, String> dataAttributes) {
		this.dataAttributes = dataAttributes;
	}

	public String generateNotification() throws IOException {
		Resource resource = new ClassPathResource("velocity.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		VelocityEngine ve = new VelocityEngine(props);
		ve.init();

		Template t = ve.getTemplate("templates/email.vm", "UTF-8");
		VelocityContext context = new VelocityContext();

		for (Map.Entry<String, String> entry : dataAttributes.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}

		// call cust api
		// populate custAttributes

		for (Map.Entry<String, String> entry : custAttributes.entrySet()) {
			context.put("username", "usernaame frorm cust api");
			context.put("productname", "product frorm cust api");
			context.put(entry.getKey(), entry.getValue());
		}

		StringWriter stringWriter = new StringWriter();
		t.merge(context, stringWriter);
		return stringWriter.toString();

	}

}
