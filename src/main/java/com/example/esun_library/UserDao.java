package com.example.esun_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PasswordEncryptionService encryptionService;

    //For Register
    public User getUserById(Integer userId){
        String sql = "SELECT user_id, phone_number, password, user_name, registration_time, last_login_time " +
                "FROM user WHERE user_id = :userId";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }

    }


    //find if phoneNumber in database
    // For Identity verification
    public User findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT phone_number, password FROM user WHERE phone_number = :phoneNumber";
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new PhoneNumberPasswordRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }

    }


    // For Register
    public Integer save(UserRequest userRequest) {
        String sql = "INSERT INTO user (phone_number, password, user_name, registration_time, last_login_time) " +
                "VALUES (:phoneNumber, :password, :userName, :registrationTime, :lastLoginTime)";
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", userRequest.getPhoneNumber());
        String encryptedPassword = encryptionService.encryptPassword(userRequest.getPassword());
        map.put("password", encryptedPassword);
        map.put("userName", userRequest.getUserName());
        Date now = new Date();
        map.put("registrationTime", now );
        map.put("lastLoginTime",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int userId = keyHolder.getKey().intValue();


        return userId;
    }

//    public Integer createUser(UserRequest userRequest) {
//        String sql = "INSERT INTO user(phone_number, password, user_name, registration_time, last_login_time) " +
//                "VALUES (:phoneNumber, :password, :userName, :registrationTime, :lastLoginTime)";
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("phoneNumber", userRequest.getPhoneNumber());
//        map.put("password", userRequest.getPassword());
//        map.put("userName", userRequest.getUserName());
//        Date now = new Date();
//        map.put("registrationTime", now );
//        map.put("lastLoginTime",now);
//
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
//
//        int userId = keyHolder.getKey().intValue();
//
//        return userId;
//    }

}
