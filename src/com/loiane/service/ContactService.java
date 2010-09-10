package com.loiane.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loiane.dao.ContactDAO;
import com.loiane.model.Contact;

/**
 * Contact Service
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
@Service
public class ContactService {
	
	private ContactDAO contactDAO;

	/**
	 * Get List of contacts from database
	 * @param start first result to get from database
	 * @param limit max of result to bring from database
	 * @return list of contacts
	 */
	public List<Contact> getContactList(int start, int limit, String dir, String sort){
		
		return contactDAO.getContacts(start, limit, dir.toUpperCase(), sort);
	}
	
	/**
	 * Get total of Contacts from database.
	 * Need to set this value on ExtJS Store
	 * @return
	 */
	public int getTotalContacts(){
		
		return contactDAO.getTotalContacts();
	}
	

	/**
	 * Spring use - DI
	 * @param contactDAO
	 */
	@Autowired
	public void setContactDAO(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	
}
