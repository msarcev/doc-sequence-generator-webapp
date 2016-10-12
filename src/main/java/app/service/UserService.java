package app.service;

import app.model.User;

public interface UserService {

    User findById(int id);
     
    User findBySso(String sso);

    void saveUser(User user);
     
}
