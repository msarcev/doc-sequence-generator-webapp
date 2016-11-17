package app.service.impl;

import app.model.User;
import app.dao.repository.UserRepository;
import app.mybatis.mappers.UserMapper;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User findById(int id) {
        return userRepository.findOne(id);
    }
 
    public User findBySso(String sso) {
        return userMapper.getUserBySsoId(sso);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
 
}
