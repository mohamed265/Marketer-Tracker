
package com.mohamed265.marketertracker.managedBean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.util.jsf.annotation.SpringRequestScoped;

@ManagedBean(name = "menuBean")
@SpringRequestScoped
@Controller
public class MenuBean extends BaseBean {

	@PostConstruct
	public void inti() {
	}

	public String navigatTo(String pagename) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagename + ".xhtml");
		} catch (IOException e) {
			logger.error(e);
		}
		return "";
	}

}