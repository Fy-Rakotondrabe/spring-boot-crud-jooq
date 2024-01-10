package com.example.db.services;

import java.util.*;
import java.util.stream.Collectors;

import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jooq.Record;

import com.example.db.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<Map<String, Object>> findUsers() {
    Result<Record> result = userRepository.findUsers();
    List<Map<String, Object>> users = result.intoMaps().stream().map(user -> {
      Map<String, Object> mappedUser = new HashMap<>(user);
      Map<String, Object> role = Map.of("id", user.get("role_id"), "name", user.get("role"));
      mappedUser.remove("role_id");
      mappedUser.remove("role");
      mappedUser.put("role", role);
      return mappedUser;
    })
        .collect(Collectors.toList());
    return users;
  }

  public Map<String, Object> findUserById(int id) {
    Result<Record> result = userRepository.findUserById(id);
    Map<String, Object> user = result.intoMaps().get(0);
    Map<String, Object> mappedUser = new HashMap<>(user);
    Map<String, Object> role = Map.of("id", user.get("role_id"), "name", user.get("role"));
    mappedUser.remove("role_id");
    mappedUser.remove("role");
    mappedUser.put("role", role);
    return mappedUser;
  }

  public void saveUser(Map<String, Object> user) {
    userRepository.saveUser(user);
  }

  public void updateUser(int id, Map<String, Object> user) {
    userRepository.updateUser(id, user);
  }

  public void deleteUser(int id) {
    userRepository.deleteUser(id);
  }
}
