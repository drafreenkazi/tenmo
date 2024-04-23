package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.TransferModel;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.tenmo.model.Transfer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller to provide account details of users.
 */
@RestController
public class TransferController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    //private AccountDao accountDao;
    //private UserDao userDao;
    private TransferDao transferDao;

    public TransferController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,TransferDao transferDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.transferDao = transferDao;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/transfer/", method = RequestMethod.POST)
    public int transfer(@Valid @RequestBody TransferModel transfer) {
        int transferComplete = 0;
        try {
            transferComplete = transferDao.transfer(transfer);
            if (transferComplete == -1 ) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender's Account Balance lesser than the requested amount");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return transferComplete;
    }
}
