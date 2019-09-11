package br.com.softblue.bluefood.util;

import java.util.Collection;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StringUtils {

	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	public static String encrypt(String rawString) {
		if (StringUtils.isEmpty(rawString)) {
			return null;
		}
		
		PasswordEncoder pwdEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return pwdEncoder.encode(rawString);
	}
	
	public static String concatenate(Collection<String> strings) {
		if (strings == null || strings.size() == 0) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		String delimiter = ", ";
		boolean first = true;
		
		for (String string : strings) {
			if (!first) {
				sb.append(delimiter);
			}
			sb.append(string);
			first = false;
		}
		
		return sb.toString();
	}
}
