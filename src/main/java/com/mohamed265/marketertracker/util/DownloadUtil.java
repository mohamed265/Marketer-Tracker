package com.mohamed265.marketertracker.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 *
 * @author Murad R. Imanbayli <muradimanbayli at gmail.com>
 */
@WebServlet(name = "DownloadUtil", urlPatterns = { "/download" })
public class DownloadUtil extends HttpServlet {

	private Logger logger = Logger.getLogger(DownloadUtil.class);

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String path = (String) request.getParameter("path");
			if (path != null) {
				File file = new File(path);

				if (file.exists()) {
					String contentType = getServletContext().getMimeType(path);
					response.setContentType(contentType);

					FileInputStream in = new FileInputStream(file);
					OutputStream out = response.getOutputStream();

					byte[] buffer = new byte[1024];
					int length;

					while ((length = in.read(buffer)) > 0) {
						out.write(buffer, 0, length);
					}

					in.close();

					response.setStatus(HttpServletResponse.SC_OK);
				} else {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}

		} catch (Exception e) {
			logger.error(e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}