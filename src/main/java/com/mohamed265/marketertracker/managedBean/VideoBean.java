
package com.mohamed265.marketertracker.managedBean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.service.VideoService;
import com.mohamed265.marketertracker.util.DateFactory;
import com.mohamed265.marketertracker.util.FileUtil;
import com.mohamed265.marketertracker.util.PropertiesReader;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@ManagedBean(name = "videoBean")
@SpringViewScoped
@Controller
public class VideoBean extends BaseBean {

	final private static Logger logger = Logger.getLogger(VideoBean.class);

	@Autowired
	private VideoService videoService;

	private List<Video> videos;

	private HashMap<Integer, Boolean> booleans;

	private UploadedFile file;

	private Video video;

	@PostConstruct
	public void inti() {
		video = new Video();
		videos = videoService.getAllVideo();
		booleans = new HashMap<>();
		for (Video video : videos) {
			booleans.put(video.getId(), video.getEnabled());
		}
	}

	public String upload() {
		FacesMessage message = null;
		if (file != null) {
			InputStream uploadedInputStream = null;

			try {
				uploadedInputStream = file.getInputstream();

				String fileName = DateFactory.getCurrentDateAsString() + "_" + file.getFileName();
				File fileobj = new File(PropertiesReader.getUploadPath(), fileName);
				int status = FileUtil.writeToFile(uploadedInputStream, fileobj);
				video.setDate(new Date());
				video.setEnabled(true);
				video.setVideoPath(fileobj.getAbsolutePath());
				video.setExtention(fileName.substring(fileName.lastIndexOf('.')));
				logger.info("Succesful" + file.getFileName() + " is uploaded, path: " + fileobj.getAbsolutePath());
				if (status == HttpServletResponse.SC_OK) {
					status = videoService.save(video) != null ? HttpServletResponse.SC_OK
							: HttpServletResponse.SC_BAD_REQUEST;

					if (status == HttpServletResponse.SC_OK) {
						message = new FacesMessage("Succesful",
								video.getName() + video.getExtention() + " is uploaded");
						videos.add(video);
						booleans.put(video.getId(), video.getEnabled());
					} else
						message = new FacesMessage("Fail", "fail to upload" + file.getFileName());
				}

			} catch (IOException e) {
				message = new FacesMessage("Fail", "fail to upload" + file.getFileName());
				logger.error(e);
			} catch (Exception e) {
				message = new FacesMessage("Fail", "fail to upload" + file.getFileName());
				logger.error(e);
			}
		} else {
			message = new FacesMessage("Fail", "fail to upload");
		}
		video = new Video();
		file = null;
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "videos";
	}

	public String deleteVideo(Video video) {
		FacesMessage message = null;
		try {
			int status = FileUtil.deleteFile(new File(video.getVideoPath()));
			logger.info("Succesful " + video.getVideoPath() + " is deleted");
			// if (status == HttpServletResponse.SC_OK) {
			status = videoService.delete(video) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST;

			if (status == HttpServletResponse.SC_OK) {
				message = new FacesMessage("Succesful", video.getName() + video.getExtention() + " is deleted");
				videos.remove(video);
				booleans.remove(video.getId());
			} else {
				logger.error("fail to delete " + video.getName() + video.getExtention() + " from database");
				message = new FacesMessage("Fail", "fail to delete" + video.getVideoPath());
			}
			// }
		} catch (Exception e) {
			message = new FacesMessage("Fail", "fail to delete " + video.getName() + video.getExtention());
			logger.error(e);
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "";
	}

	public String updateVideo(Video video) {
		FacesMessage message = null;
		try {
			video.setEnabled(!booleans.get(video.getId()));
			if (videoService.save(video) != null) {
				message = new FacesMessage("Succesful", video.getName() + video.getExtention() + " is updated");
				booleans.put(video.getId(), video.getEnabled());
			} else
				message = new FacesMessage("Fail", "fail to update" + video.getName() + video.getExtention());

		} catch (Exception e) {
			message = new FacesMessage("Fail", "fail to update" + video.getName() + video.getExtention());
			logger.error(e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		return "";
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public HashMap<Integer, Boolean> getBooleans() {
		return booleans;
	}

	public void setBooleans(HashMap<Integer, Boolean> booleans) {
		this.booleans = booleans;
	}

}
