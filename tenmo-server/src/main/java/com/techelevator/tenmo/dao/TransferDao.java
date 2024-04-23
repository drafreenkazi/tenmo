package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferModel;

public interface TransferDao {
    int transfer(TransferModel transfer);
}
