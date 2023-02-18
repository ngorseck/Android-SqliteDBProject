package com.isi.sqliteappuser.dao;

import com.isi.sqliteappuser.entities.User;

import java.util.List;

public interface IUser {
    public long add(User user);
    public long update(User user);
    public long delete(int id);
    public User get(int id);
    public List<User> getAll();
    public User login(String emailParam, String passwordParam);
}
