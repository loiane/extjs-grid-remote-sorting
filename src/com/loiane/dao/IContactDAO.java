package com.loiane.dao;

import java.util.List;

import com.loiane.model.Contact;

public interface IContactDAO {

	List<Contact> getContacts(int start, int limit, String dir, String sort);

	int getTotalContacts();
	
}
