/**
 * @author mohamed265
 * Created On : Mar 25, 2017 10:46:57 AM
 */
package com.mohamed265.marketertracker.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author mohamed265
 *
 */
public class PropertiesReader {

	private static final Logger logger = Logger.getLogger(PropertiesReader.class);

	private static String uploadPath = null;

	private static String defaultUploadPath = "/";
	
	private static String centerPoint = null;

	private static String defaultcenterPoint = "31.1975585, 29.8874076";

	public static String getUploadPath() {
		if (uploadPath == null) {
			Properties prop = new Properties();
			try {
				prop.load(PropertiesReader.class.getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE));

				uploadPath = prop.getProperty(Constants.Propeties.UPLOAD_PATH);

			} catch (IOException ex) {
				logger.error(ex);
				return defaultUploadPath;
			}
		}
		return uploadPath;
	}

	public static String getCenterPoint() {
		if (centerPoint == null) {
			Properties prop = new Properties();
			try {
				prop.load(PropertiesReader.class.getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE));

				centerPoint = prop.getProperty(Constants.Propeties.DEFUALT_CENTER_POINT);

			} catch (IOException ex) {
				logger.error(ex);
				return defaultcenterPoint;
			}
		}
		return centerPoint;
	}

}
