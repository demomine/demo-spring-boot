package com.lance.demo.springboot.controller;

import com.lance.demo.springboot.config.Page;
import com.lance.demo.springboot.dao.entity.User;
import com.lance.demo.springboot.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.selectById(id);
    }

    @GetMapping("/name/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.selectByName(name);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        if (userService.insert(user)) {
            return user;
        }
        return null;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        User userOld = userService.selectById(user.getId());
        user.setVersion(userOld.getVersion());
        userService.updateById(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteById(id);
    }

    @GetMapping("/user/{age}/pagination")
    Page<User> getUsers(@PathVariable int age,@ModelAttribute Page<User> page) {
        return userService.getUsers(page,age);
    }
}
