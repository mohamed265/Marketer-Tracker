package com.mohamed265.marketertracker.dao.daoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohamed265.marketertracker.dao.ImageTrackDao;
import com.mohamed265.marketertracker.entity.ImageTrack;

/**
 * 
 * @author mohamed265
 *
 */
@Repository("ImageTrackDao")
public class ImageTrackDaoImpl implements ImageTrackDao {
	final private static Logger logger = Logger.getLogger(ImageTrackDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(ImageTrack imageTrack) throws Exception {
		em.persist(imageTrack);
	}

	@Override
	public List<ImageTrack> getAllImageTracksByDate(Date date) {
		Query query = em.createNamedQuery("ImageTrack.getAllByDate", ImageTrack.class);
		query.setParameter("cdate", date);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ImageTrack> getAllImageTracksByUser(int userId) {
		Query query = em.createNamedQuery("ImageTrack.getAllByUser", ImageTrack.class);
		query.setParameter("userId", userId);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ImageTrack> getAllImageTracksByUserAndDate(int id, Date date) {
		Query query = em.createNamedQuery("ImageTrack.getAllImageTrackByUserAndDate", ImageTrack.class);
		query.setParameter("userId", id);
		query.setParameter("cdate", date, TemporalType.DATE);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}