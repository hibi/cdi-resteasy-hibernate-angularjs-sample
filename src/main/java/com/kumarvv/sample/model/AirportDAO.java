package com.kumarvv.sample.model;

import org.apache.log4j.Logger;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *  Sample AirportDAO
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AirportDAO {

	@PersistenceContext
	EntityManager em;

	@Inject
	Logger log;

	public List<Airport> getAll() {
		return em.createQuery("select c from Airport c", Airport.class)
				  .setMaxResults(50)
				  .getResultList();
	}

	public List<Airport> getByString(String str) {
		return em.createQuery("select c from Airport c " +
					  "where c.code like :str or c.name like :str or c.country like :str",
					  Airport.class)
				  .setParameter("str", "%" + str + "%")
				  .setMaxResults(50)
				  .getResultList();
	}

	public Airport getOne(Long id) {
		return em.find(Airport.class, id);
	}

	public Airport getOne(String code) {
		try {
			return em.createQuery("select c from Airport c where c.code = :code", Airport.class)
					  .setParameter("code", code)
					  .getSingleResult();
		} catch (NoResultException nre) {
			log.error(nre);
			return getNoResultObject();
		}
	}

	public Airport insert(Airport airport) {
		try {
			em.persist(airport);
		} catch (Exception e) {
			log.error(e);
		}
		return airport;
	}

	public Airport update(Airport airport) {
		try {
			em.merge(airport);
		} catch (Exception e) {
			log.error(e);
		}
		return airport;
	}

	public void remove(Long id) {
		Airport airport = em.find(Airport.class, id);
		try {
			if (airport != null) {
				em.remove(airport);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	public Airport getNoResultObject() {
		return null;
	}
}
