
package com.mohamed265.marketertracker.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.entity.ImageTrack;
import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.service.ImageTrackService;
import com.mohamed265.marketertracker.service.UserService;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@ManagedBean(name = "imageTrackBean")
@SpringViewScoped
@Controller
public class ImageTrackBean extends BaseBean {

	final private static Logger logger = Logger.getLogger(ImageTrackBean.class);

	@Autowired
	private ImageTrackService imageTrackService;

	@Autowired
	private UserService userService;

	private int id = 0;

	private List<User> users;

	private List<ImageTrack> imageTracks;

	private Date date;

	@PostConstruct
	public void inti() {
		users = userService.getAllUsers();
		imageTracks = new ArrayList<>();
		date = new Date();
	}

	public String changeUser() {

		User user = getUserByid();
		//date = new Date();
		imageTracks.clear();
		if (user != null) {
			imageTracks = imageTrackService.getAllImageTracksByUserAndDate(user, date);
		}

		return "";
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

	public List<ImageTrack> getImageTracks() {
		return imageTracks;
	}

	public void setImageTracks(List<ImageTrack> imageTracks) {
		this.imageTracks = imageTracks;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
