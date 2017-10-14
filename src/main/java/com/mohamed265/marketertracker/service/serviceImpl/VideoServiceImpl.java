package com.mohamed265.marketertracker.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohamed265.marketertracker.dao.GroupDao;
import com.mohamed265.marketertracker.dao.VideoDao;
import com.mohamed265.marketertracker.entity.Group;
import com.mohamed265.marketertracker.entity.GroupUser;
import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.entity.VideoGroup;
import com.mohamed265.marketertracker.entity.VideoUser;
import com.mohamed265.marketertracker.service.VideoService;

@Service("VideoService")
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoDao videoDao;

	@Autowired
	private GroupDao groupDao;

	@Override
	public Video save(Video video) {
		return videoDao.save(video);
	}

	@Override
	public VideoUser save(VideoUser videoUser) {
		return videoDao.save(videoUser);
	}

	@Override
	public VideoGroup save(VideoGroup videoGroup) {
		List<GroupUser> groupUsers = groupDao.getAllGroups(videoGroup.getGroup().getId());
		for (GroupUser groupUser : groupUsers) {
			videoDao.save(new VideoUser(videoGroup.getVideo(), groupUser.getUser()));
		}
		return videoDao.save(videoGroup);
	}

	@Override
	public List<Video> getAllVideo() {
		return videoDao.getAllVideo();
	}

	@Override
	public List<Video> getAllEnabledVideo() {
		return videoDao.getAllEnabledVideo();
	}

	@Override
	public List<VideoUser> getAllVideoUserByUser(User user) {
		return videoDao.getAllVideoUserByUser(user.getId());
	}

	@Override
	public List<VideoUser> getAllVideoUserByVideo(Video video) {
		return videoDao.getAllVideoUserByVideo(video.getId());
	}

	@Override
	public boolean delete(Video video) {
		return videoDao.delete(video);
	}

	@Override
	public boolean delete(VideoUser videoUser) {
		return videoDao.delete(videoUser);
	}

	@Override
	@Transactional
	public boolean delete(VideoGroup videoGroup) {
		videoDao.deleteAllVideoUserByVideo(videoGroup.getVideo().getId());
		return videoDao.delete(videoGroup);
	}

	@Override
	public List<VideoGroup> getAllVideoGroupByGroup(Group group) {
		return videoDao.getAllVideoGroupByGroup(group.getId());
	}

}
