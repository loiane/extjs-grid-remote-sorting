package com.loiane.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loiane.service.ContactService;

/**
 * Controller - Spring
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
@Controller
public class ContactController  {

	private ContactService contactService;
	
	@RequestMapping(value="/contact/view.action")
	public @ResponseBody Map<String,? extends Object> view(@RequestParam int start, @RequestParam int limit,
			@RequestParam String dir, @RequestParam String sort) throws Exception {

		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		
		try{
			
			modelMap.put("total", contactService.getTotalContacts());
			modelMap.put("data", contactService.getContactList(start, limit, dir, sort));
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			
			e.printStackTrace();
			
			modelMap.put("success", false);

			return modelMap;
		}
	}
	

	@Autowired
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

}
