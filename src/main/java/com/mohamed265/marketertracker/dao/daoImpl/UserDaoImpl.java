package com.mohamed265.marketertracker.dao.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohamed265.marketertracker.dao.UserDao;
import com.mohamed265.marketertracker.entity.User;

/**
 * 
 * @author mohamed265
 *
 */
@Repository("UserDao")
public class UserDaoImpl implements UserDao {

	final private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(User user) throws Exception {
		em.persist(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		em.merge(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		List<User> list = em.createNamedQuery("User.getAll").getResultList();
		return list;
	}

	@Override
	public User getUserById(int id) {
		Query query = em.createNamedQuery("User.getUserById", User.class);
		query.setParameter("ID", id);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(User user) {
		Query query = em.createNamedQuery("User.deleteUser");
		query.setParameter("ID", user.getId());
		query.executeUpdate();
	}

	@Override
	public User loginByEmail(String email, String password) {
		Query query = em.createNamedQuery("User.loginEmail");
		query.setParameter("EMAIL", email);
		query.setParameter("PASSWORD", password);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public String selecColumntByIDNative(String columnName, Object columnValue) {
		Query query = em.createNativeQuery(
				"SELECT " + columnName + " FROM user WHERE " + columnName + " = '" + columnValue + "'");
		try {
			return (String) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}