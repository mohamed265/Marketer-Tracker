package com.mohamed265.marketertracker.entity;

import java.util.ArrayList;
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
import javax.persistence.Transient;

/**
 * @author mohamed265
 *
 */
@Entity
@Table(name = "groups")
@NamedQueries({ @NamedQuery(name = "Group.getAll", query = "SELECT e FROM Group e"),
		@NamedQuery(name = "Group.getGroupById", query = "SELECT e FROM Group e WHERE e.id = :ID"),
		@NamedQuery(name = "Group.deleteGroup", query = "DELETE FROM Group e WHERE e.id = :ID") })
public class Group {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
	private List<GroupUser> users;

	@Transient
	private List<VideoGroup> allVideoGroupByGroup;

	public Group() {
		allVideoGroupByGroup = new ArrayList<>();
	}

	public Group(int id) {
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

	public List<GroupUser> getUsers() {
		return users;
	}

	public void setUsers(List<GroupUser> users) {
		this.users = users;
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
		Group other = (Group) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void setVideos(List<VideoGroup> allVideoGroupByGroup) {
		this.allVideoGroupByGroup = allVideoGroupByGroup;
	}

	public List<VideoGroup> getAllVideoGroupByGroup() {
		return allVideoGroupByGroup;
	}

	public void setAllVideoGroupByGroup(List<VideoGroup> allVideoGroupByGroup) {
		this.allVideoGroupByGroup = allVideoGroupByGroup;
	}

}
