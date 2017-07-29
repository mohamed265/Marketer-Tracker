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

import com.mohamed265.marketertracker.dao.UserTrackerDao;
import com.mohamed265.marketertracker.entity.UserTracker;

/**
 * 
 * @author mohamed265
 *
 */
@Repository("UserTrackerDao")
public class UserTrackerDaoImpl implements UserTrackerDao {
	final private static Logger logger = Logger.getLogger(UserTrackerDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(UserTracker userTracker) throws Exception {
		em.persist(userTracker);
	}

	@Override
	public List<UserTracker> getAllUserTrackerByDate(Date date) {
		Query query = em.createNamedQuery("UserTracker.getAllByDate", UserTracker.class);
		query.setParameter("cdate", date);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserTracker> getAllUserTrackersByUser(int id) {
		Query query = em.createNamedQuery("UserTracker.getAllUserTrackersByUser", UserTracker.class);
		query.setParameter("userId", id);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserTracker> getAllUserTrackersByUser(int id, Date date) {
		Query query = em.createNamedQuery("UserTracker.getAllUserTrackersByUserAndDate", UserTracker.class);
		query.setParameter("userId", id);
		query.setParameter("DATE", date, TemporalType.DATE);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}