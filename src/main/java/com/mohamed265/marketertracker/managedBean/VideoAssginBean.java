
package com.mohamed265.marketertracker.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.entity.VideoUser;
import com.mohamed265.marketertracker.service.UserService;
import com.mohamed265.marketertracker.service.VideoService;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@ManagedBean(name = "videoAssginBean")
@SpringViewScoped
@Controller
public class VideoAssginBean extends BaseBean {

	final private static Logger logger = Logger.getLogger(VideoAssginBean.class);

	@Autowired
	private VideoService videoService;

	@Autowired
	private UserService userService;

	private List<Video> videos;

	private DualListModel<Video> assginedVideos;

	private int id = 0;

	private List<User> users;

	@PostConstruct
	public void inti() {
		videos = videoService.getAllVideo();
		users = userService.getAllUsers();
		for (User user : users) {
			user.setVideos(videoService.getAllVideoUserByUser(user));
		}
		assginedVideos = new DualListModel<>();
	}

	public String changeUser() {

		assginedVideos.getSource().clear();
		assginedVideos.getTarget().clear();

		User user = getUserByid();

		if (user != null) {
			for (VideoUser videoUser : user.getVideos()) {
				assginedVideos.getTarget().add(videoUser.getVideo());
			}
			for (Video video : videos) {
				if (!assginedVideos.getTarget().contains(video)) {
					assginedVideos.getSource().add(video);
				}
			}
		}
		return "";
	}

	public void onTransfer(TransferEvent event) {

		FacesMessage message = null;
		try {
			User user = getUserByid();

			if (user == null)
				return;

			for (int i = 0; i < assginedVideos.getSource().size(); i++) {//
				Video video = getVideoById(assginedVideos.getSource().get(i));
				for (VideoUser vu : user.getVideos()) {
					if (vu.getVideo().getId() == video.getId()) {
						videoService.delete(vu);
						user.getVideos().remove(vu);
						break;
					}
				}
			}
			boolean found = false;
			for (int i = 0; i < assginedVideos.getTarget().size(); i++) {// getVideoById
				Video video = getVideoById(assginedVideos.getTarget().get(i));
				found = false;
				for (VideoUser vu : user.getVideos()) {
					if (vu.getVideo().getId() == video.getId()) {
						found = true;
						break;
					}
				}
				if (!found) {
					VideoUser vs = videoService.save(new VideoUser(video, user));
					user.getVideos().add(vs);
				}
			}
			message = new FacesMessage("Succesful", " Assignation updated");
		} catch (Exception e) {
			message = new FacesMessage("Fail", " Assignation Fail");
			logger.error(e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private User getUserByid() {
		if (id != 0)
			for (User usr : users) {
				if (id == usr.getId()) {
					return usr;
				}
			}
		return null;
	}

	private Video getVideoById(Object videoI) {
		int videoId = Integer.parseInt(videoI.toString());
		if (videoId != 0)
			for (Video video : videos) {
				if (videoId == video.getId()) {
					return video;
				}
			}
		return null;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public DualListModel<Video> getAssginedVideos() {
		return assginedVideos;
	}

	public void setAssginedVideos(DualListModel<Video> assginedVideos) {
		this.assginedVideos = assginedVideos;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
