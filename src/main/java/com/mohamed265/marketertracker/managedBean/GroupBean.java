
package com.mohamed265.marketertracker.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.entity.Group;
import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.service.GroupService;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringRequestScoped;

@SpringRequestScoped
@ManagedBean(name = "groupBean")
@Controller
public class GroupBean extends BaseBean {

	@Autowired
	private GroupService groupService;

	private Group group;

	private List<Group> groups;

	@PostConstruct
	public void inti() {
		group = new Group();
		groups = groupService.getAllGroups();
	}

	public String save() {
		if (groupService.save(group)) {
			groups.add(group);
			addSuccessfulMessage("Group Added Successfully");
			group = new Group();
		} else {
			addErrorMessage("Internal Error, please try later");
		}

		return "";
	}

	public String deleteGroup(Group group) {
		try {
			groups.remove(group);
			groupService.delete(group);
			addSuccessfulMessage("Group Deleted Successfully");
		} catch (Exception e) {
			addErrorMessage("Internal Error, please try later");
		}

		return "";
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}
