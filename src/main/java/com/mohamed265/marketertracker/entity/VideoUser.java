package com.mohamed265.marketertracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author mohamed265
 *
 */
@Entity
@Table(name = "video_user")
@NamedQueries({
		@NamedQuery(name = "VideoUser.getAllByVideo", query = "SELECT e FROM VideoUser e WHERE e.video.id = :vedioId"),
		@NamedQuery(name = "VideoUser.getAllByUser", query = "SELECT e FROM VideoUser e WHERE e.user.id = :userId"),
		@NamedQuery(name = "VideoUser.deleteAllByVideo", query = "DELETE FROM VideoUser e WHERE e.video.id = :videoId") })
public class VideoUser {

	@Id
	@GeneratedValue
	private int id;

	@JoinColumn(name = "vedio_id", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Video video;

	@JoinColumn(name = "user_id", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private User user;

	public VideoUser() {
	}

	public VideoUser(Video video, User user) {
		this.video = video;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "VideoUser [id=" + id + ", video=" + video + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoUser other = (VideoUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
