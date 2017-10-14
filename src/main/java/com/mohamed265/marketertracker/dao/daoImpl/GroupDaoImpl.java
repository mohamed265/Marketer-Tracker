package com.mohamed265.marketertracker.dao.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohamed265.marketertracker.dao.GroupDao;
import com.mohamed265.marketertracker.entity.Group;
import com.mohamed265.marketertracker.entity.GroupUser;

/**
 * 
 * @author mohamed265
 *
 */
@Repository("GroupDao")
public class GroupDaoImpl implements GroupDao {

	final private static Logger logger = Logger.getLogger(GroupDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Group group) throws Exception {
		em.persist(group);
	}

	@Override
	@Transactional
	public GroupUser save(GroupUser groupUser) throws Exception {
		return em.merge(groupUser);
	}

	@Override
	@Transactional
	public void update(Group group) {
		em.merge(group);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAllGroups() {
		List<Group> list = em.createNamedQuery("Group.getAll").getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupUser> getAllGroups(int groupId) {
		Query query = em.createNamedQuery("GroupUser.getAllByGroup");
		query.setParameter("groupId", groupId);
		return query.getResultList();
	}

	@Override
	public Group getGroupById(int id) {
		Query query = em.createNamedQuery("Group.getGroupById", Group.class);
		query.setParameter("ID", id);
		try {
			return (Group) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(Group group) {
		Query query = em.createNamedQuery("Group.deleteGroup");
		query.setParameter("ID", group.getId());
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void deleteGroupUserByGroup(int groupUserId) {
		Query query = em.createNamedQuery("GroupUser.deleteByGroup");
		query.setParameter("groupId", groupUserId);
		query.executeUpdate();
	}

	@Override
	public String selecColumntByIDNative(String columnName, Object columnValue) {
		Query query = em.createNativeQuery(
				"SELECT " + columnName + " FROM group WHERE " + columnName + " = '" + columnValue + "'");
		try {
			return (String) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(GroupUser groupUser) {
		Query query = em.createNamedQuery("GroupUser.deleteByGroupAndUser");
		query.setParameter("groupId", groupUser.getGroup().getId());
		query.setParameter("userId", groupUser.getUser().getId());
		query.executeUpdate();
	}

	@Override
	public List<GroupUser> getAllGroupUsers() {
		List<GroupUser> list = em.createNamedQuery("GroupUser.getAll").getResultList();
		return list;
	}

	@Override
	@Transactional
	public void deleteAllGroupVideoByGroup(int groupId) {
		Query query = em.createNamedQuery("VideoGroup.deleteAllByGroup");
		query.setParameter("groupId", groupId);
		query.executeUpdate();

	}

}