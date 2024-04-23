package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.UsersListModel;
import com.techelevator.tenmo.model.User;

import java.text.DecimalFormat;
import java.util.List;

public interface UserDao {

    List<User> findAll();
    List<UsersListModel> listUsersForTransfer(String username);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);

}
