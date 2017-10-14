
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

import com.mohamed265.marketertracker.entity.Group;
import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.entity.VideoGroup;
import com.mohamed265.marketertracker.service.GroupService;
import com.mohamed265.marketertracker.service.UserService;
import com.mohamed265.marketertracker.service.VideoService;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@SpringViewScoped
@ManagedBean(name = "videoGroupAssignBean")
@Controller
public class VideoGroupAssignBean extends BaseBean {

	final private static Logger logger = Logger.getLogger(VideoGroupAssignBean.class);

	@Autowired
	private VideoService videoService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	private List<Video> videos;

	private DualListModel<Video> assginedVideos;

	private int id = 0;

	private List<Group> groups;

	@PostConstruct
	public void inti() {
		videos = videoService.getAllVideo();
		groups = groupService.getAllGroups();
		for (Group group : groups) {
			group.setVideos(videoService.getAllVideoGroupByGroup(group));
		}
		assginedVideos = new DualListModel<>();
	}

	public String changeGroup() {

		assginedVideos.getSource().clear();
		assginedVideos.getTarget().clear();

		Group group = getGroupByid();

		if (group != null) {
			for (VideoGroup VideoGroup : group.getAllVideoGroupByGroup()) {
				assginedVideos.getTarget().add(VideoGroup.getVideo());
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
			Group group = getGroupByid();

			if (group == null)
				return;

			for (int i = 0; i < assginedVideos.getSource().size(); i++) {//
				Video video = getVideoById(assginedVideos.getSource().get(i));
				for (VideoGroup vg : group.getAllVideoGroupByGroup()) {
					if (vg.getVideo().getId() == video.getId()) {
						videoService.delete(vg);
						group.getAllVideoGroupByGroup().remove(vg);
						break;
					}
				}
			}
			boolean found = false;
			for (int i = 0; i < assginedVideos.getTarget().size(); i++) {// getVideoById
				Video video = getVideoById(assginedVideos.getTarget().get(i));
				found = false;
				for (VideoGroup vg : group.getAllVideoGroupByGroup()) {
					if (vg.getVideo().getId() == video.getId()) {
						found = true;
						break;
					}
				}
				if (!found) {
					VideoGroup vg = videoService.save(new VideoGroup(video, group));
					group.getAllVideoGroupByGroup().add(vg);
				}
			}
			message = new FacesMessage("Succesful", " Assignation updated");
		} catch (Exception e) {
			message = new FacesMessage("Fail", " Assignation Fail");
			logger.error(e);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private Group getGroupByid() {
		if (id != 0)
			for (Group group : groups) {
				if (id == group.getId()) {
					return group;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}
