
package com.mohamed265.marketertracker.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@ManagedBean(name = "homeBean")
@SpringViewScoped
@Controller
public class HomeBean extends BaseBean {

	// @Autowired
	// private WeatherDayService weatherDayService;
	//
	// @Autowired
	// private ImageTrackService imageTrackService;

	@PostConstruct
	public void inti() {

	}

}