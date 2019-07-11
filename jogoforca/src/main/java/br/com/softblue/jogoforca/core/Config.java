package br.com.softblue.jogoforca.core;

import java.io.IOException;
import java.util.Properties;

public class Config {

	private static Properties props = new Properties();
	
	static {
		try {
			props.load(Config.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String get(String name) {
		return props.getProperty(name);
	}
	
	public static void setMaxErrors(String maxErrors) {
		props.setProperty("maxErrors", maxErrors);
	}
}
