
package com.mohamed265.marketertracker.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.service.UserService;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringRequestScoped;

@ManagedBean(name = "loginBean")
@SpringRequestScoped
@Controller
public class LoginBean extends BaseBean {

	@Autowired
	private UserService userService;

	private String input;
	private String password;

	@PostConstruct
	public void inti() {
	}

	private boolean isEmail() {
		int atIndex = input.indexOf('@');

		if (atIndex == -1)
			return false;

		int lastDotIndex = input.lastIndexOf('.');

		return atIndex < lastDotIndex && lastDotIndex - atIndex != 1 && lastDotIndex != input.length() - 1
				&& atIndex != 0;
	}

	public String validate() {
		User user = null;
		if (isEmail()) {
			user = userService.loginByEmail(input, password);
		} else {
			addErrorMessage("invaild login parameter");
			return "";
		}

		if (user == null)
			addErrorMessage("invaild email or password");
		else if (user.getIsAdmin()) {
			setSessionUser(user);
			redirectToHomePage();
		} else {
			addErrorMessage("invaild user type");
		}
		return "";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index?faces-redirect=true";
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}