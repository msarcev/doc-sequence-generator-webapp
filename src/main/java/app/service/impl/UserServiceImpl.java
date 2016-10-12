package app.service.impl;

import app.dao.UserDao;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserDao dao;
 
    public User findById(int id) {
        return dao.findById(id);
    }
 
    public User findBySso(String sso) {
        return dao.findBySSO(sso);
    }

    public void saveUser(User user){
        dao.saveUser(user);
    }
 
}
