package com.techelevator.tenmo.model;

import java.util.Objects;

public class UsersListModel {

   private Long id;
   private String username;

   public UsersListModel() { }


   public UsersListModel(Long id, String username) {
      this.id = id;
      this.username = username;
   }
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }



   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      UsersListModel user = (UsersListModel) o;
      return id == user.id &&

              Objects.equals(username, user.username) ;
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, username);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", username='" + username + '\'' +
              '}';
   }
}
