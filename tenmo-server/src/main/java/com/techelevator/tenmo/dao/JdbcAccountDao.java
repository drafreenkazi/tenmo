package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Double balanceByUserName(String username) {

        try {
            String sql = "Select balance from account " +
                    "inner join tenmo_user on account.user_id = tenmo_user.user_id " +
                    " WHERE username ILIKE ?;";
            Double balance = jdbcTemplate.queryForObject(sql, Double.class, username);
            if (balance != null) {
                return balance;
            } else {
                return 0.0;
            }
        }
         catch (
             CannotGetJdbcConnectionException e) {
                 throw new DaoException("Unable to connect to server or database", e);
        } catch (
            DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
        }
    }
}
