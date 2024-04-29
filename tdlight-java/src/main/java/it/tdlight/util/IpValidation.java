package it.tdlight.util;

import java.util.regex.Pattern;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/4/29 星期一 23:11
 * @descriptor:
 */
public class IpValidation {
	private static final String IPADDRESS_PATTERN =
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private static final int MIN_PORT_NUMBER = 0;
	private static final int MAX_PORT_NUMBER = 65535;

	public static boolean validateIP(String ipAddress) {
		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		return pattern.matcher(ipAddress).matches();
	}

	public static boolean validatePort(int port) {
		return port >= MIN_PORT_NUMBER && port <= MAX_PORT_NUMBER;
	}
}
