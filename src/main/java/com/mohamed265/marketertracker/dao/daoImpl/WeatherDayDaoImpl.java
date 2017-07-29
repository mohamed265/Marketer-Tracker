package com.mohamed265.marketertracker.dao.daoImpl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohamed265.marketertracker.dao.WeatherDayDao;
import com.mohamed265.marketertracker.entity.Video;

/**
 * 
 * @author mohamed265
 *
 */
@Repository("WeatherDayDao")
public class WeatherDayDaoImpl implements WeatherDayDao {

	final private static Logger logger = Logger.getLogger(WeatherDayDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Video video) throws Exception {
		em.persist(video);
	}

	@Override
	public Video getWeatherDayByDate(Date date) {
		Query query = em.createNamedQuery("Video.getByDate", Video.class);
		query.setParameter("cdate", date);
		try {
			return (Video) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}