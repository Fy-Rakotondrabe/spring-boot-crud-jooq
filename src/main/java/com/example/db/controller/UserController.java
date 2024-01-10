package com.example.db.controller;

import org.springframework.web.bind.annotation.*;

import com.example.db.repositories.UserRepository;
import com.example.db.services.UserService;

import java.util.*;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public List<Map<String, Object>> getAllUsers() {
    List<Map<String, Object>> users = userService.findUsers();
    return users;
  }

  @GetMapping("/{id}")
  public Map<String, Object> getOneUser(@PathVariable int id) {
    Map<String, Object> user = userService.findUserById(id);
    return user;
  }

  @PostMapping
  public String createUser(@RequestBody Map<String, Object> user) {
    userService.saveUser(user);
    return "User created: " + user;
  }

  @PutMapping("/{id}")
  public String updateUser(@PathVariable int id, @RequestBody Map<String, Object> user) {
    userService.updateUser(id, user);
    return "User updated: " + user;
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable int id) {
    userService.deleteUser(id);
    return "User deleted";
  }
}