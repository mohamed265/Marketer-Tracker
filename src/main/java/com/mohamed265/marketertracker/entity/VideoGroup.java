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
@Table(name = "video_group")
@NamedQueries({
		@NamedQuery(name = "VideoGroup.getAllByVideo", query = "SELECT e FROM VideoGroup e WHERE e.video.id = :vedioId"),
		@NamedQuery(name = "VideoGroup.getAllByGroup", query = "SELECT e FROM VideoGroup e WHERE e.group.id = :groupId"),
		@NamedQuery(name = "VideoGroup.deleteAllByVideo", query = "DELETE FROM VideoGroup e WHERE e.video.id = :videoId"),
		@NamedQuery(name = "VideoGroup.deleteAllByGroup", query = "DELETE FROM VideoGroup e WHERE e.group.id = :groupId")})
public class VideoGroup {

	@Id
	@GeneratedValue
	private int id;

	@JoinColumn(name = "vedio_id", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Video video;

	@JoinColumn(name = "group_id", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Group group;

	public VideoGroup() {
	}

	public VideoGroup(Video video, Group group) {
		this.video = video;
		this.group = group;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "VideoGroup [id=" + id + ", video=" + video + ", group=" + group + "]";
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
		VideoGroup other = (VideoGroup) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
