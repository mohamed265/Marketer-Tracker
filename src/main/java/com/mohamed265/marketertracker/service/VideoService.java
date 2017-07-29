package com.mohamed265.marketertracker.service;

import java.util.List;

import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.entity.VideoUser;

public interface VideoService {

	public Video save(Video video);

	public VideoUser save(VideoUser videoUser);
	
	public boolean delete(Video video);

	public boolean delete(VideoUser videoUser);

	public List<Video> getAllVideo();

	public List<Video> getAllEnabledVideo();

	public List<VideoUser> getAllVideoUserByUser(User user);

	public List<VideoUser> getAllVideoUserByVideo(Video video);

}