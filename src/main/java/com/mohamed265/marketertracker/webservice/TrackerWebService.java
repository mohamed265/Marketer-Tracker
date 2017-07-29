package com.mohamed265.marketertracker.webservice;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.mohamed265.marketertracker.entity.ImageTrack;
import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.UserTracker;
import com.mohamed265.marketertracker.entity.VideoUser;
import com.mohamed265.marketertracker.service.ImageTrackService;
import com.mohamed265.marketertracker.service.UserTrackerService;
import com.mohamed265.marketertracker.service.VideoService;
import com.mohamed265.marketertracker.util.Constants;
import com.mohamed265.marketertracker.util.DateFactory;
import com.mohamed265.marketertracker.util.FileUtil;
import com.mohamed265.marketertracker.util.PropertiesReader;

/**
 * @author mohamed265
 */
@Path("/tracker")
public class TrackerWebService {

	private Logger logger = Logger.getLogger(TrackerWebService.class);

	@Autowired
	private UserTrackerService userTrackerService;

	@Autowired
	private ImageTrackService imageTrackService;

	@Autowired
	private VideoService videoService;

	public TrackerWebService() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@POST
	@Path("/trackImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@QueryParam(Constants.Tracker.userId) String userId,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		try {
			int usrId = Integer.parseInt(userId);
			String fileName = DateFactory.getCurrentDateAsString() + "_" + fileDetail.getFileName();
			File fileobj = new File(PropertiesReader.getUploadPath(), fileName);
			int status = FileUtil.writeToFile(uploadedInputStream, fileobj);

			if (status == HttpServletResponse.SC_OK) {
				status = imageTrackService.save(new ImageTrack(new Date(), fileobj.getAbsolutePath(), new User(usrId)))
						? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST;
			}

			return Response.status(status).build();
		} catch (Exception e) {
			logger.error(e);
			return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
		}

	}

	@GET
	@Path("/trackMap")
	public Response uploadLatLang(@QueryParam(Constants.Tracker.userId) String userId,
			@QueryParam(Constants.Tracker.LATITUDE) String latitude,
			@QueryParam(Constants.Tracker.LONGITUDE) String longitude) {
		try {
			UserTracker userTracker = new UserTracker();
			userTracker.setDate(new Date());
			userTracker.setUser(new User(Integer.parseInt(userId)));
			userTracker.setLatitude(latitude);
			userTracker.setLongitude(longitude);
			int response = (userTrackerService.save(userTracker) ? HttpServletResponse.SC_OK
					: HttpServletResponse.SC_BAD_REQUEST);
			return Response.status(response).build();
		} catch (Exception e) {
			logger.error(e);
			return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/trackVideo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VideoUser> getVideos(@QueryParam(Constants.Tracker.userId) String userId) {
		List<VideoUser> list;
		try {
			list = videoService.getAllVideoUserByUser(new User(Integer.parseInt(userId)));
			for (VideoUser videoUser : list) {
				videoUser.setUser(null);
			}
		} catch (Exception e) {
			list = new ArrayList<>();
			logger.error(e);
		}
		return list;
	}

}

// private static final String FILE_PATH =
// "D:\\TRANING_MATERIALS\\MATRIAL\\CodeConventions.pdf";
//
// @GET
// @Path("/get")
// @Produces("application/pdf")
// public Response getFile() {
//
// File file = new File(FILE_PATH);
//
// ResponseBuilder response = Response.ok((Object) file);
// response.header("Content-Disposition", "attachment;
// filename=new-android-book.pdf");
// return response.build();
//
// }
//
// @GET
// @Path("/get2")
// @Produces(MediaType.TEXT_PLAIN)
// public Response streamExample() {
// StreamingOutput stream = new StreamingOutput() {
// @Override
// public void write(OutputStream os) throws IOException,
// WebApplicationException {
// Writer writer = new BufferedWriter(new OutputStreamWriter(os));
// writer.write("test");
// writer.flush(); // <-- This is very important. Do not forget.
// }
// };
// return Response.ok(stream).build();
// }
//
// final String path = "D:\\Video\\teet.mp4";
//
// @GET
// @Path("/get3")
// @Produces(MediaType.APPLICATION_OCTET_STREAM)
// public Response doTest() throws Exception {
// InputStream is = new FileInputStream(path);
// ByteArrayOutputStream baos = new ByteArrayOutputStream();
// int len;
// byte[] buffer = new byte[4096];
// while ((len = is.read(buffer, 0, buffer.length)) != -1) {
// baos.write(buffer, 0, len);
// }
// System.out.println("Server size: " + baos.size());
// return Response.ok(baos).build();
// }
