
package com.mohamed265.marketertracker.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.service.ImageTrackService;
import com.mohamed265.marketertracker.service.WeatherDayService;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@ManagedBean(name = "homeBean")
@SpringViewScoped
@Controller
public class HomeBean extends BaseBean {

	@Autowired
	private WeatherDayService weatherDayService;

	@Autowired
	private ImageTrackService imageTrackService;

	private Video video;

	// private List<Note> notes;

	@PostConstruct
	public void inti() {

	}

	public Video getWeatherDay() {
		return video;
	}

	// public List<Note> getNotes() {
	// return null;
	// }

}