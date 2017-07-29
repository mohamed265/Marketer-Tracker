package com.mohamed265.marketertracker.entity;

import java.util.Comparator;
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

import com.mohamed265.marketertracker.util.Constants;

/**
 * @author mohamed265
 *
 */
@Entity
@Table(name = "user_tracker")
@NamedQueries({
		@NamedQuery(name = "UserTracker.getAllByDate", query = "SELECT e FROM UserTracker e WHERE e.date = :cdate ORDER BY e.id"),
		@NamedQuery(name = "UserTracker.getAllUserTrackersByUser", query = "SELECT e FROM UserTracker e WHERE e.user.id = :userId ORDER BY e.id"),
		@NamedQuery(name = "UserTracker.getAllUserTrackersByUserAndDate", query = "SELECT e FROM UserTracker e WHERE e.user.id = :userId AND e.date = :DATE ORDER BY e.id")

})
public class UserTracker implements Comparator<UserTracker> {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "date")
	private Date date;

	@Column(name = Constants.Tracker.LATITUDE)
	private String latitude;

	@Column(name = Constants.Tracker.LONGITUDE)
	private String longitude;

	@JoinColumn(name = "user_id", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private User user;

	public UserTracker() {
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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserTracker [id=" + id + ", date=" + date + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", user=" + user + "]";
	}

	@Override
	public int compare(UserTracker o1, UserTracker o2) {
		return o1.getId() - o2.getId();
	}

}
