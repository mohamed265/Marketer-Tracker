package com.mohamed265.marketertracker.service;

import java.util.Date;
import java.util.List;

import com.mohamed265.marketertracker.entity.ImageTrack;
import com.mohamed265.marketertracker.entity.User;

public interface ImageTrackService {

	public boolean save(ImageTrack imageTrack);

	public List<ImageTrack> getAllImageTracksByUserAndDate(User user, Date date);

	public List<ImageTrack> getAllImageTracksByDate(Date date);
	
	public List<ImageTrack> getAllImageTracksByUser(User user);

}