package com.example.esun_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncryptionService encryptionService;

//    @GetMapping("/users/{phoneNumber}")
//    public ResponseEntity<User> getUser(@PathVariable String phoneNumber) {
//
//        User user = userService.findByPhoneNumber(phoneNumber);
//
//        if (user != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//    }

    // For Identity verification
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserRequest userRequest) {
        // 根據用戶輸入的手機號碼查詢用戶資料
        User user = userService.findByPhoneNumber(userRequest.getPhoneNumber());

        if (user != null ) {
            // 從用戶資料中獲取儲存的密碼
            String storedPassword = user.getPassword();

            // 將儲存的密碼與前端傳來的密碼進行驗證
            if (encryptionService.verifyPassword(userRequest.getPassword(), storedPassword)) {
                // 密碼匹配，登入成功

                return ResponseEntity.ok("Login successful");
            } else {
                // 密碼不匹配，登入失敗
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            // 未找到用戶資料，登入失敗
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    // For Register
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest) {
        // 調用 UserService 的註冊方法
        Integer userId = userService.register(userRequest);

        User registeredUser = userService.getUserById(userId);
        if (registeredUser != null) {
            // 如果註冊成功，返回註冊成功的用戶對象及狀態碼201
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } else {
            // 如果手機號碼已經存在，返回衝突的狀態碼409
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }





//    @PostMapping("/users")
//    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
//        Integer userId = userService.createUser(userRequest);
//
//        User user = userService.getUserById(userId);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }


}
