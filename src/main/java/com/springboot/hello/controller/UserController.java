package com.springboot.hello.controller;

import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("")
    public User get() {
        return userDao.findById("1");
    }

    @PostMapping("/add")
    public String add() throws SQLException {
        userDao.add(new User("1", "suin", "1234"));
        return "추가 완료";
    }

    @DeleteMapping("/all")
    public ResponseEntity<Integer> deleteAll() throws SQLException {
        return ResponseEntity
                .ok()
                .body(userDao.deleteAll());
    }
}