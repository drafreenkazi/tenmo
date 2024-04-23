package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import java.util.Objects;

public class TransferModel {



   private Long userIdFrom;
   private Long userIdTo;
   @Min(value = 1, message = "The minimum transfer amount is 1")
   private Double transferAmount;
   public Long getUserIdFrom() {
      return userIdFrom;
   }

   public void setUserIdFrom(Long userIdFrom) {
      this.userIdFrom = userIdFrom;
   }

   public Long getUserIdTo() {
      return userIdTo;
   }

   public void setUserIdTo(Long userIdTo) {
      this.userIdTo = userIdTo;
   }
   public Double getTransferAmount() {
      return transferAmount;
   }

   public void setTransferAmount(Double transferAmount) {
      this.transferAmount = transferAmount;
   }

   public TransferModel() { }


   public TransferModel(Long userIdFrom, Long userIdTo, Double transferAmount) {
      this.userIdFrom = userIdFrom;
      this.userIdTo = userIdTo;
      this.transferAmount =transferAmount;
   }


   @Override
   public String toString() {
      return "TransferModel{" +
              "userIdFrom=" + userIdFrom +
              "userIdTo=" + userIdTo +
              ", transferAmount='" + transferAmount + '\'' +
              '}';
   }
}
