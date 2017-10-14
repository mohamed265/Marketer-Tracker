package com.mohamed265.marketertracker.dao.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohamed265.marketertracker.dao.VideoDao;
import com.mohamed265.marketertracker.entity.Video;
import com.mohamed265.marketertracker.entity.VideoGroup;
import com.mohamed265.marketertracker.entity.VideoUser;

@Repository("VideoDao")
public class VideoDaoImpl implements VideoDao {

	private Logger logger = Logger.getLogger(VideoDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Video save(Video video) {
		try {
			return em.merge(video);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	@Transactional
	public VideoUser save(VideoUser videoUser) {
		try {
			return em.merge(videoUser);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	@Transactional
	public VideoGroup save(VideoGroup videoGroup) {
		try {
			return em.merge(videoGroup);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Video> getAllVideo() {
		Query query = em.createNamedQuery("Video.AllVideo", Video.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Video> getAllEnabledVideo() {
		Query query = em.createNamedQuery("Video.AllEnabledVideo", Video.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<VideoUser> getAllVideoUserByUser(int userId) {
		Query query = em.createNamedQuery("VideoUser.getAllByUser", VideoUser.class);
		query.setParameter("userId", userId);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<VideoUser> getAllVideoUserByVideo(int videoId) {
		Query query = em.createNamedQuery("VideoUser.getAllByVideo", VideoUser.class);
		query.setParameter("vedioId", videoId);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	@Transactional
	public boolean delete(Video video) {
		try {
			Query query = em.createNamedQuery("VideoUser.deleteAllByVideo");
			query.setParameter("videoId", video.getId());
			query.executeUpdate();
			video = em.find(Video.class, video.getId());
			em.remove(video);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean delete(VideoUser videoUser) {
		try {
			videoUser = em.find(VideoUser.class, videoUser.getId());
			em.remove(videoUser);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean delete(VideoGroup videoGroup) {
		try {
			videoGroup = em.find(VideoGroup.class, videoGroup.getId());
			em.remove(videoGroup);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	@Override
	public List<VideoGroup> getAllVideoGroupByGroup(int groupId) {
		try {
			Query query = em.createNamedQuery("VideoGroup.getAllByGroup", VideoGroup.class);
			query.setParameter("groupId", groupId);
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void deleteAllVideoUserByVideo(int videoID) {
		try {
			Query query = em.createNamedQuery("VideoUser.deleteAllByVideo");
			query.setParameter("videoId", videoID);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
