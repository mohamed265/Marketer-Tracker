package com.mohamed265.marketertracker.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author mohamed265
 *
 */
@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "User.getAll", query = "SELECT e FROM User e"),
		@NamedQuery(name = "User.getUserById", query = "SELECT e FROM User e WHERE e.id = :ID"),
		@NamedQuery(name = "User.deleteUser", query = "DELETE FROM User e WHERE e.id = :ID"),
		@NamedQuery(name = "User.loginEmail", query = "SELECT e FROM User e WHERE e.email = :EMAIL and e.password = :PASSWORD") })
public class User {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	// @Column(name = "username")
	// private String username;

	@Column(name = "isAdmin")
	private Boolean isAdmin;

	@Column(name = "isActive")
	private Boolean isActive;

	@OneToMany(mappedBy = "video", fetch = FetchType.EAGER)
	private List<VideoUser> videos;

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// public String getUsername() {
	// return username;
	// }
	//
	// public void setUsername(String username) {
	// this.username = username;
	// }

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<VideoUser> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoUser> videos) {
		this.videos = videos;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", isAdmin="
				+ isAdmin + ", isActive=" + isActive + ", videos=" + videos + "]";
	}

}
