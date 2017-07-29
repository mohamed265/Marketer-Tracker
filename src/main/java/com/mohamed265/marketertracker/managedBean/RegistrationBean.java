
package com.mohamed265.marketertracker.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.service.UserService;
import com.mohamed265.marketertracker.util.Constants;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringRequestScoped;

@SpringRequestScoped
@ManagedBean(name = "registrationBean")
@Controller
public class RegistrationBean extends BaseBean {

	@Autowired
	private UserService userService;

	private User user;

	private String password;

	private String confirmationPassword;
	private String errorMessage;
	private String errorMessageEmail;

	@PostConstruct
	public void inti() {
		user = new User();
	}

	public String save() {
		// if (password.equals(confirmationPassword)) {
		// user.setPassword(password);
		// } else {
		// addErrorMessage("password not matched");
		// return "";
		// }
		//
		// if (!isEmail()) {
		// addErrorMessage("Not Vaild Email");
		// return "";
		// }
		user.setPassword(Constants.PASSWORD);
		if (!userService.isUniqeEmail(user.getEmail())) {
			addErrorMessage("Email Alredy Exists");
			return "";
		}

		// user.setIsAdmin(false);

		if (userService.save(user)) {
			addSuccessfulMessage("User Added Successfully");

			user = new User();
			// setSessionUser(user);
			// redirectToHomePage();
		} else {
			addErrorMessage("Internal Error, please try later");
		}

		return "";
	}

	private boolean isEmail() {
		int atIndex = user.getEmail().indexOf('@');

		if (atIndex == -1)
			return false;

		int lastDotIndex = user.getEmail().lastIndexOf('.');

		return atIndex < lastDotIndex && lastDotIndex - atIndex != 1 && lastDotIndex != user.getEmail().length() - 1
				&& atIndex != 0;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public User getUser() {
		return user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessageEmail() {
		return errorMessageEmail;
	}

	public void setErrorMessageEmail(String errorMessageEmail) {
		this.errorMessageEmail = errorMessageEmail;
	}

}
