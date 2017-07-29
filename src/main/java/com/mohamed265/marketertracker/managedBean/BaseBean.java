package com.mohamed265.marketertracker.managedBean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.util.Constants;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@SpringViewScoped
public class BaseBean {

	final protected static Logger logger = Logger.getLogger(BaseBean.class);

	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(true);
	}

	public void setSessionUser(User user) {
		getExternalContext().getSessionMap().put(Constants.SESSION_USER, user);
	}

	public User getSessionUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (User) context.getExternalContext().getSessionMap().get(Constants.SESSION_USER);
	}

	public Integer getSessionUserID() {
		return getSessionUser().getId();
	}

	public void redirect(String pageName) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pageName);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public String getContextPath() {
		return getExternalContext().getRequestContextPath();
	}

	public void redirectToHomePage() {
		redirect(getContextPath() + "/home.xhtml");
	}

	public void redirectToIndexPage() {
		redirect(getContextPath() + "/index.xhtml");
	}

	public void addSuccessfulMessage(String message) {
		FacesMessage meg = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, meg);

	}

	public void addErrorMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public void addFatalMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, message, ""));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public void addInfoMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public void addWarnMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
}
