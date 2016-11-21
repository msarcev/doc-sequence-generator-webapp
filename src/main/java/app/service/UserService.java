package app.service;

import app.model.User;
import app.model.UserProfile;

import java.util.Set;

public interface UserService {

    User findById(int id);
     
    User findBySso(String sso);

    Set<UserProfile> getUserAuthorities(User user);

    void saveUser(User user);
     
}
