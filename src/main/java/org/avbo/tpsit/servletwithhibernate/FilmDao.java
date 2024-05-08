package org.avbo.tpsit.servletwithhibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.*;

public class FilmDao {
	/**
	 * Get Film By ID
	 * 
	 * @param id
	 * @return
	 */
	public Film getFilm(int id) {

		Transaction transaction = null;
		Film user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			user = session.get(Film.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Get all Films
	 * 
	 * @return
	 */
	public List<Film> getAllFilms() {

		Transaction transaction = null;
		List<Film> listOfUser = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object

			// Get Criteria Builder
			CriteriaBuilder builder = session.getCriteriaBuilder();

			// Create Criteria
			CriteriaQuery<Film> criteria = builder.createQuery(Film.class);
			Root<Film> contactRoot = criteria.from(Film.class);
			criteria.select(contactRoot);

			// Use criteria to query with session to fetch all contacts
			listOfUser = session.createQuery(criteria).getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfUser;
	}
}
