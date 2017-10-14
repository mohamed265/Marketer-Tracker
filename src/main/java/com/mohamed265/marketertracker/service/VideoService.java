package com.mohamed265.marketertracker.service;

import java.util.List;

import com.mohamed265.marketertracker.entity.Group;
import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.entity.VideoGroup;
import com.mohamed265.marketertracker.entity.VideoUser;

public interface VideoService {

	public Video save(Video video);

	public VideoUser save(VideoUser videoUser);

	public VideoGroup save(VideoGroup videoGroup);

	public boolean delete(Video video);

	public boolean delete(VideoUser videoUser);

	public boolean delete(VideoGroup videoGroup);

	public List<Video> getAllVideo();

	public List<Video> getAllEnabledVideo();

	public List<VideoUser> getAllVideoUserByUser(User user);

	public List<VideoUser> getAllVideoUserByVideo(Video video);

	public List<VideoGroup> getAllVideoGroupByGroup(Group group);

}