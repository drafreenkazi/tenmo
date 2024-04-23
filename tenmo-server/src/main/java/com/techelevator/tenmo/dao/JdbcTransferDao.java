package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferModel;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.techelevator.tenmo.exception.*;

import java.text.DecimalFormat;

@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int transfer(TransferModel transfer) {
        Integer transferId;
        //Get account id from Sender and receiver

        String sqlAccountFrom = "Select account_id from account WHERE user_id = ?;";
        int accountFrom = jdbcTemplate.queryForObject(sqlAccountFrom, Integer.class, transfer.getUserIdFrom());
        String sqlAccountTo = "Select account_id from account WHERE user_id = ?;";
        int accountTo = jdbcTemplate.queryForObject(sqlAccountFrom, Integer.class, transfer.getUserIdTo());

        //check balance of the sender
        String sqlBalance = "Select balance from account WHERE account_id = ?;";
        Double balance = jdbcTemplate.queryForObject(sqlBalance, Double.class, accountFrom);
        if (balance > transfer.getTransferAmount())
        {
            try {
            // create transfer
                String sqlTransfer = "INSERT INTO transfer (transfer_type_id, transfer_status_id, " +
                    "account_from, account_to,amount) VALUES (?, ?,?,?,? ) RETURNING transfer_id";
                //setting transfer type as Send and Status as Approved
                transferId = jdbcTemplate.queryForObject(sqlTransfer, Integer.class, 0, 1, accountFrom, accountTo, transfer.getTransferAmount());
            //update sender balance
                String sqlSender = "Update account set balance = balance - ? where account_id = ?";
                int rowsAffectedSender = jdbcTemplate.update(sqlSender, transfer.getTransferAmount(), accountFrom);
                if (rowsAffectedSender == 0) {
                    throw new DaoException("Zero rows affected for sender, expected at least one");
                }
            //update receiver balance
                    String sqlReceiver = "Update account set balance = balance + ? where account_id = ?";
                    int rowsAffectedReceiver = jdbcTemplate.update(sqlReceiver, transfer.getTransferAmount(),accountTo);
                    if (rowsAffectedReceiver == 0) {
                        throw new DaoException("Zero rows affected for receiver , expected at least one");
                    }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        }
        else
        {
            //if balance is lesser than transfer amount, send back -1 as transfer id, process respective error message to user
            return -1;
        }
        return transferId;
    }
}
