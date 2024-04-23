package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.UsersListModel;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller to provide account details of users.
 */
@RestController
public class AccountController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private AccountDao accountDao;
    private UserDao userDao;

    public AccountController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao,AccountDao accountDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/balance/{userName}", method = RequestMethod.GET)
    public Double getBalance(@PathVariable String userName) {
        Double balance = 0.0;
        try {
             balance = accountDao.balanceByUserName(userName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Get Balance By User Name failed.");
        }
        return balance;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/listUsersForTransfer/{userName}", method = RequestMethod.GET)
    public List<UsersListModel> listUsersForTransfer(@PathVariable String userName) {
        List<UsersListModel> users = new ArrayList<>();
        try {
            users = userDao.listUsersForTransfer(userName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List Users For Transfer failed.");
        }
        return users;
    }
}
