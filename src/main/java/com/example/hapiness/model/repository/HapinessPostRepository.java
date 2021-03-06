package com.example.hapiness.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.example.hapiness.model.entity.post.HapinessPostCreateEntity;
import com.example.hapiness.model.entity.post.HapinessPostEntity;
import com.example.hapiness.model.entity.post.HapinessPostFindOneEntity;

//interface
@Repository
public class HapinessPostRepository {

	static final String URL = "jdbc:mysql://localhost:3306/hapiness?serverTimezone=JST";
	static final String USERNAME = "root";
	static final String PASSWORD = "ny09304869";

	List<HapinessPostEntity> hapinessPostList = null;
	@Autowired
	@Lazy
	private HapinessPostEntity hapinessPostEntity;
	HapinessPostFindOneEntity hapinessPostFindOneEntity = new HapinessPostFindOneEntity();
	List list = null;

	public List<HapinessPostEntity> findAll() {

		@SuppressWarnings("unchecked")

		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(HapinessPostEntity.class)
				.buildSessionFactory();

		Session session = sf.getCurrentSession();

		try {
			session.beginTransaction();
			hapinessPostList = (List<HapinessPostEntity>) session.createQuery("FROM HapinessPostEntity").list();
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			sf.close();
		}
		return hapinessPostList;
	}

	//	public List findByuserIdAndCreateDateTime(int userId, String createDateTime) {
	//
	//		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")
	//				.addAnnotatedClass(HapinessPostEntity.class)
	//				.buildSessionFactory();
	//
	//		Session session = sf.getCurrentSession();
	//		try {
	//			session.beginTransaction();
	//			Query query = session.createQuery(
	//					"FROM HapinessPostEntity hpe WHERE hpe.hapinessPostPK.userId= :i AND hpe.hapinessPostPK.createDateTime= :d");
	//			query.setParameter("i", userId);
	//			query.setParameter("d", createDateTime);
	//			list = query.list();
	//			System.out.println(list.get(0));
	//			((HashMap<String, String>)list.get(0)).get("body");
	//			hapinessPostFindOneEntity.setBody(((HashMap<String, String>)list.get(0)).get("body"));
	//
	//			//??????????????????https://qiita.com/ponsuke0531/items/d02386b9540c35ffb27d
	//			session.getTransaction().commit();
	//
	//		} catch (Exception e) {
	//			session.getTransaction().rollback();
	//			e.printStackTrace();
	//		} finally {
	//			sf.close();
	//		}
	//		return list;
	//	}

	public HapinessPostFindOneEntity findByuserIdAndCreateDateTime(int userId, String createDateTime) {

		//hibernate???1?????????????????????????????????????????????????????????????????????
		String sql = "SELECT * FROM hppost hpe WHERE userId = ? AND createDateTime = ?;";

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			statement.setInt(1, userId);
			statement.setString(2, createDateTime);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				hapinessPostFindOneEntity.setBody(rs.getString("body"));
				hapinessPostFindOneEntity.setUserId(userId);
				hapinessPostFindOneEntity.setCreateDateTime(createDateTime);
			}
			connection.commit();

		} catch (Exception e) {
			System.out.println("error??????????????????");
			e.printStackTrace();
		} finally {
		}
		return hapinessPostFindOneEntity;
	}

	public HapinessPostFindOneEntity edit(int userId, String createDateTime) {

		//hibernate???1?????????????????????????????????????????????????????????????????????
		String sql = "SELECT * FROM hppost hpe WHERE userId = ? AND createDateTime = ?;";

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			statement.setInt(1, userId);
			statement.setString(2, createDateTime);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				hapinessPostFindOneEntity.setBody(rs.getString("body"));
				hapinessPostFindOneEntity.setUserId(userId);
				hapinessPostFindOneEntity.setCreateDateTime(createDateTime);
			}
			connection.commit();

		} catch (Exception e) {
			System.out.println("error??????????????????");
			e.printStackTrace();
		} finally {
		}
		return hapinessPostFindOneEntity;
	}

	//?????????string???????????????????????????????????????????????????????????????????????????List???????????????????????????????????????
	//????????????String???????????????split???????????????????????????????????????????????????
	public HapinessPostCreateEntity save(HapinessPostCreateEntity hapinessPostCreateEntity) {

		//hibernate???insert??????????????????????????????????????????insert????????????
		String sql = "INSERT INTO hppost VALUES (1, ?, CURRENT_TIMESTAMP);";

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			statement.setString(1, hapinessPostCreateEntity.getBody());
			statement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			System.out.println("error??????????????????");
			e.printStackTrace();
		} finally {
		}
		return hapinessPostCreateEntity;
	}

	public HapinessPostFindOneEntity update(Integer userId, String createDateTime,
			HapinessPostEntity hapinessPostEntity) {

		//HapinessPostFindOneEntity hapinessPostFindOneEntity = new HapinessPostFindOneEntity();
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(HapinessPostEntity.class)
				.buildSessionFactory();
		Session session = sf.getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(
					"UPDATE HapinessPostEntity hpe SET hpe.body= :b WHERE hpe.hapinessPostPK.userId= :i AND hpe.hapinessPostPK.createDateTime= :d");
			query.setParameter("i", userId);
			query.setParameter("d", createDateTime);
			query.setParameter("b", hapinessPostEntity.getBody());
			query.executeUpdate();
			session.getTransaction().commit();
			hapinessPostFindOneEntity.setUserId(userId);
			hapinessPostFindOneEntity.setCreateDateTime(createDateTime);
			//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????index??????????????????????????????
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			sf.close();
		}
		return hapinessPostFindOneEntity;
	}

	public void delete(Integer userId, String createDateTime) {
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(HapinessPostEntity.class).buildSessionFactory();
		Session session = sf.getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("DELETE FROM HapinessPostEntity hpe WHERE hpe.hapinessPostPK.userId= :i AND hpe.hapinessPostPK.createDateTime= :d" );
		    query.setParameter("i", userId);
		    query.setParameter("d", createDateTime);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			sf.close();
		}
	}
}
