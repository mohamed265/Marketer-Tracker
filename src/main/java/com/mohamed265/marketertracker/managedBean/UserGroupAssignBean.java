
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
import com.mohamed265.marketertracker.entity.GroupUser;
import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.entity.VideoGroup;
import com.mohamed265.marketertracker.service.GroupService;
import com.mohamed265.marketertracker.service.UserService;
import com.mohamed265.marketertracker.service.VideoService;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@SpringViewScoped
@ManagedBean(name = "userGroupAssignBean")
@Controller
public class UserGroupAssignBean extends BaseBean {

	final private static Logger logger = Logger.getLogger(UserGroupAssignBean.class);

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	private List<User> users;

	private DualListModel<GroupUser> assginedUsers;

	private int id = 0;

	private List<Group> groups;

	@PostConstruct
	public void inti() {
		users = userService.getAllUsers();
		groups = groupService.getAllGroups();
		// for (Group group : groups) {
		// group.setVideos(videoService.getAllVideoGroupByGroup(group));
		// }
		assginedUsers = new DualListModel<>();
	}

	public String changeGroup() {

		assginedUsers.getSource().clear();
		assginedUsers.getTarget().clear();

		Group group = getGroupByid();

		if (group != null) {
			for (GroupUser groupUser : group.getUsers()) {
				assginedUsers.getTarget().add(groupUser);
			}
			for (User usr : users) {
				if (!InTarget(assginedUsers.getTarget(), usr))
					assginedUsers.getSource().add(new GroupUser(group, usr));
			}
		}
		return "";

	}

	private boolean InTarget(List<GroupUser> target, User usr) {
		for (GroupUser groupUser : target) {
			if (groupUser.getUser().getId() == usr.getId())
				return true;
		}
		return false;
	}

	public void onTransfer(TransferEvent event) {

		FacesMessage message = null;
		try {
			Group group = getGroupByid();

			if (group == null)
				return;

			for (int i = 0; i < assginedUsers.getSource().size(); i++) {//
				User user = getVideoById(assginedUsers.getSource().get(i));
				for (GroupUser gu : group.getUsers()) {
					if (gu.getUser().getId() == user.getId()) {
						groupService.delete(gu);
						group.getAllVideoGroupByGroup().remove(gu);
						break;
					}
				}
			}
			boolean found = false;
			for (int i = 0; i < assginedUsers.getTarget().size(); i++) {// getVideoById
				User user = getVideoById(assginedUsers.getTarget().get(i));
				found = false;
				for (GroupUser gu : group.getUsers()) {
					if (gu.getUser().getId() == user.getId()) {
						found = true;
						break;
					}
				}
				if (!found) {
					GroupUser gu = groupService.save(new GroupUser(group, user));
					group.getUsers().add(gu);
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

	private User getVideoById(Object videoI) {
		int videoId = Integer.parseInt(videoI.toString());
		if (videoId != 0)
			for (User usr : users) {
				if (videoId == usr.getId()) {
					return usr;
				}
			}
		return null;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public DualListModel<GroupUser> getAssginedUsers() {
		return assginedUsers;
	}

	public void setAssginedUsers(DualListModel<GroupUser> assginedUsers) {
		this.assginedUsers = assginedUsers;
	}

}
