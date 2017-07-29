
package com.mohamed265.marketertracker.managedBean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.UserTracker;
import com.mohamed265.marketertracker.service.UserService;
import com.mohamed265.marketertracker.service.UserTrackerService;
import com.mohamed265.marketertracker.util.PropertiesReader;
import com.mohamed265.marketertracker.util.jsf.annotation.SpringViewScoped;

@ManagedBean(name = "userMapBean")
@SpringViewScoped
@Controller
public class UserMapBean extends BaseBean {

	final private static Logger logger = Logger.getLogger(UserMapBean.class);

	@Autowired
	private UserTrackerService userTrackerService;

	@Autowired
	private UserService userService;

	private int id = 0;

	private List<User> users;

	private MapModel simpleModel;

	private MapModel polylineModel;

	private String center;

	private Date date;

	@PostConstruct
	public void inti() {
		users = userService.getAllUsers();
		center = PropertiesReader.getCenterPoint();
		date = new Date();
	}

	public String changeUser() {

		simpleModel = new DefaultMapModel();
		polylineModel = new DefaultMapModel();
		center = PropertiesReader.getCenterPoint();
		//date = new Date();
		User user = getUserByid();

		if (user != null) {
			List<UserTracker> list = userTrackerService.getAllUserTrackerByUserAndDate(user, date);
			int i = 1;

			Polyline polyline = new Polyline();

			for (UserTracker userTracker : list) {
				LatLng coord = new LatLng(Double.parseDouble(userTracker.getLatitude()),
						Double.parseDouble(userTracker.getLongitude()));
				simpleModel.addOverlay(new Marker(coord, i + ""));
				polyline.getPaths().add(coord);
				i++;
			}

			polyline.setStrokeWeight(5);
			polyline.setStrokeColor("#FF9900");
			polyline.setStrokeOpacity(0.7);

			polylineModel.addOverlay(polyline);

			if (polyline.getPaths().size() > 0)
				center = polyline.getPaths().get(0).getLat() + ", " + polyline.getPaths().get(0).getLng();
		}

		return "";
	}

	private User getUserByid() {
		if (id != 0)
			for (User usr : users) {
				if (id == usr.getId()) {
					return usr;
				}
			}
		return null;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public MapModel getPolylineModel() {
		return polylineModel;
	}

	public void setPolylineModel(MapModel polylineModel) {
		this.polylineModel = polylineModel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
