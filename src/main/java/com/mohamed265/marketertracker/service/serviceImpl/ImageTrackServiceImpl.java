package com.mohamed265.marketertracker.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohamed265.marketertracker.dao.ImageTrackDao;
import com.mohamed265.marketertracker.entity.ImageTrack;
import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.service.ImageTrackService;

@Service("ImageTrackService")
public class ImageTrackServiceImpl implements ImageTrackService {

	private final static Logger logger = Logger.getLogger(ImageTrackServiceImpl.class);

	@Autowired
	private ImageTrackDao imageTrackDao;

	@Override
	public boolean save(ImageTrack note) {

		try {
			imageTrackDao.save(note);
			return true;
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	@Override
	public List<ImageTrack> getAllImageTracksByUserAndDate(User user, Date date) {
		return imageTrackDao.getAllImageTracksByUserAndDate(user.getId(), date);
	}

	@Override
	public List<ImageTrack> getAllImageTracksByDate(Date date) {
		return imageTrackDao.getAllImageTracksByDate(date);
	}

	@Override
	public List<ImageTrack> getAllImageTracksByUser(User user) {
		return imageTrackDao.getAllImageTracksByUser(user.getId());
	}

}
