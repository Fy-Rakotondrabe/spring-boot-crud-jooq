package com.example.db.repositories;

import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  private final DSLContext dsl;

  UserRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  public Result<Record> findUsers() {
    Result<Record> result = dsl.select().from("users").fetch();
    return result;
  }

  public Result<Record> findUserById(int id) {
    Result<Record> record = dsl.select().from("users").where("id = ?", id).fetch();
    return record;
  }

  public void saveUser(Map user) {
    dsl.query("insert into users(id, name, email, role_id) values (?, ?, ?, ?)", user.get("id"), user.get("name"),
        user.get("email"), user.get("user_role")).execute();
  }

  public void updateUser(int id, Map user) {
    dsl.query("update users set(name, email, role_id) = (?, ?, ?) where id = ?", user.get("name"),
        user.get("email"), user.get("user_role"), user.get("id")).execute();
  }

  public void deleteUser(int id) {
    dsl.query("delete * from users where id = ?", id).execute();
  }
}