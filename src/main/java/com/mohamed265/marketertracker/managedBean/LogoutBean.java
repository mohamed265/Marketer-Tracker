
package com.mohamed265.marketertracker.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.util.jsf.annotation.SpringRequestScoped;

@ManagedBean(name = "logoutBean")
@SpringRequestScoped
@Controller
public class LogoutBean extends BaseBean {

	@PostConstruct
	public void inti() {
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		redirectToIndexPage();
		//addInfoMessage("Logout Successfully");
		return "index.xhtml?faces-redirect=true";
	}

}