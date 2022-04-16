package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UsersDao extends JpaDao<Users> implements GenericDao<Users>{
	public UsersDao() {
		super();
		
	}
	
	public Users create(Users u) {
		String encryptedPassword = HashGenerator.generateMD5(u.getPassword());
		u.setPassword(encryptedPassword);		
		return super.create(u);
	}

	
	public Users update(Users u) {
		return super.update(u);
	}
	
	@Override
	public Users get(Object id) {
		return super.find(Users.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Users.class, id);
	}
	
	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	public Users findByEmail(String email) {
		return super.findByEmailQuery(email);
	}
	
	public boolean checkLogin(String email, String password) {
		Map<String, Object> params = new HashMap<>();
		String encryptedPassword = HashGenerator.generateMD5(password);
		params.put("email", email);
		params.put("password", encryptedPassword);
		
		Users user = super.findWithNamedQueryAndParam("Users.checkLogin", params);
		
		if(user != null) {
			System.out.println("user found with correct password and email" + encryptedPassword);
			return true;	
		}
		System.out.println("user not found with uncorrect password and email" + encryptedPassword);
		return false;
	}
	
	@Override
	public long count() {
		return super.countAll("Users.countAll");
		
	}
	  


}
