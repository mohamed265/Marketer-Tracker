
package com.mohamed265.marketertracker.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

public class WebServiceConnector {

	private static final Logger logger = Logger.getLogger(WebServiceConnector.class);

	public static String getResponeString(String urll) {
		try {
			URL url = new URL(urll);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();
			String inputLine = "";
			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);
			in.close();
			return response.toString();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

}
