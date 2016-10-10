package app.dao;

import app.model.User;

public interface UserDao {
	 
    User findById(int id);
     
    User findBySSO(String sso);
     
}