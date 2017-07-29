package com.mohamed265.marketertracker.dao;

import java.util.Date;
import java.util.List;

import com.mohamed265.marketertracker.entity.ImageTrack;

public interface ImageTrackDao {

	public void save(ImageTrack imageTrack) throws Exception;

	public List<ImageTrack> getAllImageTracksByDate(Date date);

	public List<ImageTrack> getAllImageTracksByUserAndDate(int id, Date date);

	public List<ImageTrack> getAllImageTracksByUser(int userId);

}
