package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Transfer {

    private int transferId;
    private int transferStatusId;

    private int accountFrom;

    private int accountTo;
    @Min(value = 1, message = "The minimum transfer amount is 1")
    private Double transferAmount;
    private String transferType;
    private String transferStatusType;

    public Transfer(int transferId, int transferStatusId, int accountFrom, int accountTo, Double transferAmount, String transferType, String transferStatusType) {
        this.transferId = transferId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.transferAmount = transferAmount;
        this.transferType = transferType;
        this.transferStatusType = transferStatusType;

    }
    public Transfer(int accountFrom, int accountTo, Double transferAmount, String transferType) {
        this.transferType = transferType;
         this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.transferAmount = transferAmount;
    }

    public Transfer() {
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }



    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferQueryType() {
        return transferType;
    }

    public void setTransferQueryType(String transferQueryType) {
        this.transferType = transferQueryType;
    }

    public String getTransferStatusType() {
        return transferStatusType;
    }

    public void setTransferStatusType(String transferStatusType) {
        this.transferStatusType = transferStatusType;
    }
}
