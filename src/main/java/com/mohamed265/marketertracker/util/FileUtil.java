package com.mohamed265.marketertracker.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class);

	public static int writeToFile(InputStream uploadedInputStream, File file) {

		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(file);
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(e);
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		return HttpServletResponse.SC_OK;
	}

	public static int deleteFile(File file) {
		return file.delete() ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST;
	}
}
