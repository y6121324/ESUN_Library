package com.example.esun_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserDao userDao;
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    // For Register
    // For Identity verification
    public User findByPhoneNumber(String phoneNumber) {
        return userDao.findByPhoneNumber(phoneNumber);
    }

    // For Register
    public Integer register(UserRequest userRequest){
        User user = userDao.findByPhoneNumber(userRequest.getPhoneNumber());
        if (user != null) {
            return null;
        }
        return userDao.save(userRequest);
    }

}
