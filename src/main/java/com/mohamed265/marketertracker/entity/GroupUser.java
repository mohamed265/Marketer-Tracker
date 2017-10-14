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
@Table(name = "group_user")
@NamedQueries({ @NamedQuery(name = "GroupUser.getAll", query = "SELECT e FROM GroupUser e"),
		@NamedQuery(name = "GroupUser.getAllByGroup", query = "SELECT e FROM GroupUser e WHERE e.group.id = :groupId"),
		@NamedQuery(name = "GroupUser.getAllByUser", query = "SELECT e FROM GroupUser e WHERE e.user.id = :userId"),
		@NamedQuery(name = "GroupUser.deleteByUser", query = "DELETE FROM GroupUser e WHERE e.user.id = :userId"),
		@NamedQuery(name = "GroupUser.deleteByGroup", query = "DELETE FROM GroupUser e WHERE e.group.id = :groupId"),
		@NamedQuery(name = "GroupUser.deleteByGroupAndUser", query = "DELETE FROM GroupUser e WHERE e.group.id = :groupId AND e.user.id = :userId") })
public class GroupUser {

	@Id
	@GeneratedValue
	private int id;

	@JoinColumn(name = "group_id", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Group group;

	@JoinColumn(name = "user_id", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private User user;

	public GroupUser() {
	}

	public GroupUser(Group group, User user) {
		this.group = group;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "VideoUser [id=" + id + ", video=" + group + ", user=" + user + "]";
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
		GroupUser other = (GroupUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
