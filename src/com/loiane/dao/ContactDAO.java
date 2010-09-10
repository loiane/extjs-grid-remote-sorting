package com.loiane.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.loiane.model.Contact;

/**
 * Contact DAO class
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
@Repository
public class ContactDAO implements IContactDAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	/**
	 * Get List of contacts from database
	 * @param start first result to get from database
	 * @param limit max of result to bring from database
	 * @return list of contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContacts(int start, int limit, String dir, String sort) {
		
		Order order;
		
		if (dir.equals("ASC")){
			order = Order.asc(sort);
		} else{
			order = Order.desc(sort);
		}
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Contact.class).addOrder(order);
		
		return hibernateTemplate.findByCriteria(criteria, start, limit);
		
	}
	
	/**
	 * Get total of Contacts from database
	 * @return
	 */
	@Override
	public int getTotalContacts(){
		
		return DataAccessUtils.intResult(hibernateTemplate.find("SELECT COUNT(*) FROM Contact"));
	}

}
