package com.example.db.controller;

import org.springframework.web.bind.annotation.*;

import com.example.db.repositories.UserRepository;

import java.util.*;

import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public List<Map<String, Object>> getAllUsers() {
    Result<Record> users = userRepository.findUsers();
    return users.intoMaps();
  }

  @GetMapping("/{id}")
  public List<Map<String, Object>> getOneUser(@PathVariable int id) {
    Result<Record> user = userRepository.findUserById(id);
    return user.intoMaps();
  }

  @PostMapping
  public String createUser(@RequestBody Map user) {
    userRepository.saveUser(user);
    return "User created: " + user;
  }

  @PutMapping("/{id}")
  public String updateUser(@PathVariable int id, @RequestBody Map user) {
    userRepository.updateUser(id, user);
    return "User updated: " + user;
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable int id) {
    userRepository.deleteUser(id);
    return "User deleted";
  }
}