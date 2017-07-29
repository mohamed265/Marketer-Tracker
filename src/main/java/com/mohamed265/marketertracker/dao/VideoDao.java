package com.mohamed265.marketertracker.dao;

import java.util.List;

import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.entity.VideoUser;

public interface VideoDao {

	public Video save(Video video);

	public VideoUser save(VideoUser videoUser);
	
	public boolean delete(Video video);

	public boolean delete(VideoUser videoUser);

	public List<Video> getAllVideo();

	public List<Video> getAllEnabledVideo();

	public List<VideoUser> getAllVideoUserByUser(int userId);

	public List<VideoUser> getAllVideoUserByVideo(int videoId);

}