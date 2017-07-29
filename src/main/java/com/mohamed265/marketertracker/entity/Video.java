package com.mohamed265.marketertracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author mohamed265
 *
 */
@Entity
@Table(name = "videos")
@NamedQueries({ @NamedQuery(name = "Video.getByDate", query = "SELECT e FROM Video e WHERE e.date = :cdate"),
		@NamedQuery(name = "Video.AllVideo", query = "SELECT e FROM Video e"),
		@NamedQuery(name = "Video.AllEnabledVideo", query = "SELECT e FROM Video e WHERE e.enabled = True") })
public class Video {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "date")
	private Date date;

	@Column(name = "name")
	private String name;

	@Column(name = "extention")
	private String extention;

	@Column(name = "videoPath")
	private String videoPath;

	@Column(name = "enabled")
	private Boolean enabled;

	public Video() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", date=" + date + ", name=" + name + ", extention=" + extention + ", videoPath="
				+ videoPath + ", enabled=" + enabled + "]";
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
		Video other = (Video) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
