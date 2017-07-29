package com.mohamed265.marketertracker.entity;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "image_track")
@NamedQueries({
		@NamedQuery(name = "ImageTrack.getAllByDate", query = "SELECT e FROM ImageTrack e WHERE e.date = :cdate ORDER BY e.id"),
		@NamedQuery(name = "ImageTrack.getAllByUser", query = "SELECT e FROM ImageTrack e WHERE e.user.id = :userId ORDER BY e.id"),
		@NamedQuery(name = "ImageTrack.getAllImageTrackByUserAndDate", query = "SELECT e FROM ImageTrack e WHERE  e.date = :cdate AND e.user.id = :userId ORDER BY e.id") })
public class ImageTrack {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "date")
	private Date date;

	@Column(name = "imgPath")
	private String imgPath;

	@JoinColumn(name = "user_id", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private User user;

	public ImageTrack() {
	}

	public ImageTrack(Date date, String imgPath, User user) {
		super();
		this.date = date;
		this.imgPath = imgPath;
		this.user = user;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ImageTrack [id=" + id + ", date=" + date + ", imgPath=" + imgPath + ", user=" + user + "]";
	}

}
